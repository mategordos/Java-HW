package com.epam.training.ticketservice.core.room.persistence.repository;

import com.epam.training.ticketservice.core.room.persistence.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, String> {
    Optional<Room> findRoomByRoomName(String roomName);
}

