package com.ra.repository;

import com.ra.model.Seed;
import com.ra.utils.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeedRepository {
    public List<Seed> findAll() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        List<Seed> seeds = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            cs = conn.prepareCall("{CALL get_all_seeds()}");
            rs = cs.executeQuery();
            while (rs.next()) {
                Seed seed = new Seed();
                seed.setId(rs.getInt("id"));
                seed.setSeedsName(rs.getString("seeds_name"));
                seed.setPrice(rs.getDouble("price"));
                seed.setImageUrl(rs.getString("image_url"));
                seeds.add(seed);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cs);
        }
        return seeds;
    }
}
