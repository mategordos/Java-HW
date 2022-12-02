package com.epam.training.ticketservice.core.room.persistence.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Room {

    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true)
    private String roomName;
    private Integer rowLength;
    private Integer columnLength;

    public Room(String roomName,Integer rowLength, Integer columnLength){
        this.roomName = roomName;
        this.rowLength = rowLength;
        this.columnLength = columnLength;
    }



    @Override
    public String toString() {
        return String.format("%s (%s, %d minutes)",
                roomName, rowLength, columnLength);
    }

}
