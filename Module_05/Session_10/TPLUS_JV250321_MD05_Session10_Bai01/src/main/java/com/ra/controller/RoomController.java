package com.ra.controller;

import com.ra.model.PaginationResult;
import com.ra.model.Room;
import com.ra.model.RoomStatus;
import com.ra.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/room")
public class RoomController {
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String listRooms(@RequestParam(name = "page", defaultValue = "1") int page,
                            @RequestParam(name = "size", defaultValue = "10") int size,
                            Model model) {
        PaginationResult<Room> pagination = roomService.getPaginationData(null, size, page);

        model.addAttribute("rooms", pagination.getDataList());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("pageSize", size);
        System.out.println(123);
        return "room/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("room", new Room());
        model.addAttribute("statuses", RoomStatus.values());
        return "room/add";
    }

    @PostMapping("/add")
    public String addRoom(@Valid @ModelAttribute("room") Room room,
                          BindingResult result,
                          Model model) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", RoomStatus.values());
            return "room/add";
        }
        boolean success = roomService.addRoom(room);
        if (success) {
            return "redirect:/room";
        } else {
            model.addAttribute("errorMsg", "Thêm phòng thất bại");
            model.addAttribute("statuses", RoomStatus.values());
            return "room/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") long id, Model model) {
        Room room = roomService.findById(id);
        if (room == null) {
            return "redirect:/room";
        }
        model.addAttribute("room", room);
        model.addAttribute("statuses", RoomStatus.values());
        return "room/edit";
    }

    @PostMapping("/edit")
    public String editRoom(@Valid @ModelAttribute("room") Room room,
                           BindingResult result,
                           Model model) {
        if (result.hasErrors()) {
            model.addAttribute("statuses", RoomStatus.values());
            return "room/edit";
        }
        boolean success = roomService.updateRoom(room);
        if (success) {
            return "redirect:/room";
        } else {
            model.addAttribute("errorMsg", "Cập nhật phòng thất bại");
            model.addAttribute("statuses", RoomStatus.values());
            return "room/edit";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteRoom(@PathVariable("id") long id) {
        roomService.deleteRoom(id);
        return "redirect:/room";
    }
}
