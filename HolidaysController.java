package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HolidaysController {
    private HolidayDao dao = new HolidayDao();

    @GetMapping("/holidays")
    public List<HolidayPojo> getAllHolidays(){
        return dao.getAll();
    }

    @GetMapping("/holidays/{id}")
    public HolidayPojo getHoliday(@PathVariable int id){
        return dao.get(id);
    }

    @PostMapping("/holidays")
    public boolean add(@RequestBody HolidayPojo holiday){
        return dao.add(holiday);
    }

    @PutMapping("/holidays/{id}")
    public boolean update(@RequestBody HolidayPojo holiday, @PathVariable int id){
        holiday.id = id;
        return dao.update(holiday);
    }

    @DeleteMapping("/holidays/{id}")
    public boolean remove(@PathVariable int id){
        return dao.delete(dao.get(id));
    }
}
