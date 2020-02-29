package com.github.MaxLebedev2000.points;

import com.github.MaxLebedev2000.db.points.Point;
import com.github.MaxLebedev2000.db.points.PointsRepository;
import com.github.MaxLebedev2000.db.users.User;
import com.github.MaxLebedev2000.db.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.*;

import static java.lang.Math.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PointsController {

    @Autowired
    private PointsRepository points;

    @Autowired
    private UsersRepository users;

    private Checker checker = (x, y, r) -> {


        if (r <= 0) {
            return false;
        } else if (x < 0 && y < 0) {
            return false;
        } else if (x < 0 && y > 0 && pow(x, 2) + pow(y, 2) < pow(r / 2, 2)) {
            return true;
        } else if (x >= 0 && y >= 0 && x <= r / 2 && y <= r) {
            return true;
        } else if (x >= 0 && y <= 0 && y >= x - r) {
            return true;
        } else {
            return false;
        }

    };

    @RequestMapping(value = "/api/points", method = RequestMethod.GET)
    public Object getPoints(@RequestHeader(name = "token", required = false) String token) {
        System.out.println("Ask points");
        User u = users.getByToken(token);
        if (u == null) {
            Map<String, String> resp = new HashMap<>();
            resp.put("auth", "failed");
            return resp;
        }

        String login = u.getLogin();
        System.out.println(points.findByOwner(login));
        return points.findByOwner(login);
    }

    private String cut(String str) {
        if (str.length() > 5) {
            return str.substring(0, 5);
        } else {
            return str;
        }
    }

    @RequestMapping(value = "/api/points", method = RequestMethod.POST)
    public Object addPoint(@RequestBody String body, @RequestHeader(name = "token", required = false) String token) {
        GsonJsonParser parser = new GsonJsonParser();
        System.out.println(body );
        Map<String, Object> params = parser.parseMap(body);
        String sx = params.getOrDefault("x", "").toString();
        String sy = params.getOrDefault("y", "").toString();
        String sr = params.getOrDefault("r", "").toString();

        float x = Float.parseFloat(cut(sx));
        float y = Float.parseFloat(cut(sy));
        float r = Float.parseFloat(cut(sr));
        boolean res = checker.check(x, y, r);


        User user = users.getByToken(token);
        if (user == null) {
            Map<String, String> resp = new HashMap<>();
            resp.put("auth", "failed");
            return resp;
        }

        String login = user.getLogin();
        Point point = new Point(x, y, r, res, login);
        this.points.save(point);
        return point;
    }


}
