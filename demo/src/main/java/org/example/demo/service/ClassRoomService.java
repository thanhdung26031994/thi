package org.example.demo.service;

import org.example.demo.config.DBConnection;
import org.example.demo.model.ClassRoom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClassRoomService implements IClassRoomService{

    @Override
    public List<ClassRoom> getAllClass() {
        List<ClassRoom> classRooms = new ArrayList<>();
        Connection connection;
        PreparedStatement statement = null;
        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement("select * from classroom;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Integer id = rs.getInt("id");
                String name = rs.getString("name");
                classRooms.add(new ClassRoom(id, name));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return classRooms;
    }
}
