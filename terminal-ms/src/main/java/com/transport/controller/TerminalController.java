package com.transport.controller;

import com.transport.dto.TerminalDTO;
import com.transport.exception.TerminalException;
import com.transport.service.TerminalServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ftr/terminals")
public class TerminalController {
    @Autowired
    private TerminalServiceImpl terminalService;

    @GetMapping
    public ResponseEntity<List<TerminalDTO>> fetchFTRTerminals() {
        return ResponseEntity.ok(terminalService.fetchFTRTerminals());
    }

    @GetMapping("/{terminalId}")
    public ResponseEntity<TerminalDTO> fetchTerminalByTerminalId(@PathVariable String terminalId) throws TerminalException {
        return ResponseEntity.ok(terminalService.fetchFTRTerminalByTerminalId(terminalId));
    }

    @GetMapping("/itemType/{itemType}")
    public ResponseEntity<List<TerminalDTO>> fetchTerminalsByItemType(@PathVariable String itemType) throws TerminalException {
        return ResponseEntity.ok(terminalService.fetchTerminalsByItemType(itemType));
    }

    @PostMapping
    public ResponseEntity<TerminalDTO> insertNewTerminal(@Valid @RequestBody TerminalDTO terminalDTO) {
        return ResponseEntity.ok(terminalService.insertNewTerminal(terminalDTO));
    }

    @PutMapping("/{terminalId}/{newCapacity}")
    public ResponseEntity<String> updateTerminal(@PathVariable String terminalId, @PathVariable Integer newCapacity) {
        return ResponseEntity.ok(terminalService.updateTerminal(terminalId, newCapacity));
    }

    @DeleteMapping("/{terminalId}")
    public ResponseEntity<String> removeTerminal(@PathVariable String terminalId) {
        return ResponseEntity.ok(terminalService.removeTerminal(terminalId));
    }



}
