package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    @GetMapping("/holidays")
    public String displayHolidays(@RequestParam(required = false) boolean festival,
                                  @RequestParam(required = false) boolean federal,
                                  @RequestParam(required = false) boolean traditional,
                                  Model model) {
        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);
        model.addAttribute("traditional", traditional);
        List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL),
                new Holiday(" Dec 14 ", "Gloonch's birthday", Holiday.Type.FEDERAL),
                new Holiday(" Dec 14 ", "Glootie's Funeral", Holiday.Type.TRADITIONAL)
        );

        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type: types){
            model.addAttribute(type.toString(), holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList()));
        }
        return "holidays.html";
    }
}
