package com.woyo.racecondition.controller;

import com.woyo.racecondition.dto.TransferRequestDto;
import com.woyo.racecondition.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    @PostMapping
    private String transfer(@RequestBody TransferRequestDto transferRequestDto) {
        return transferService.transfer(transferRequestDto);
    }
}
