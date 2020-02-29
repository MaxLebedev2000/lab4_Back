package com.github.MaxLebedev2000.db.points;

import org.springframework.data.repository.Repository;

import java.util.List;

public interface PointsRepository extends Repository<Point, String> {

    void save(Point point);
    List<Point> findByOwner(String owner);

}
