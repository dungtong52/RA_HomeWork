package com.ra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {
    private Long id;
    private String roomName;
    private String roomType;
    private RoomStatus status;
    private boolean isDelete;
    private double price;
    private String image;
}
