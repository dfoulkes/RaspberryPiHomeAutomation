package com.foulkes.lights.mvc.frontend.model;

/**
 * Created by danfoulkes on 30/10/15.
 */
public class RoomItem {

    private String RoomId;
    private String roomName;
    private String icon;

    public String getRoomId() {
        return RoomId;
    }

    public void setRoomId(String roomId) {
        RoomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
