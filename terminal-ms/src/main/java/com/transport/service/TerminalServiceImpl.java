package com.transport.service;

import com.transport.dto.TerminalDTO;
import com.transport.entity.Terminal;
import com.transport.exception.TerminalException;
import com.transport.repository.TerminalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TerminalServiceImpl implements TerminalService {

    @Autowired
    private TerminalRepository terminalRepository;

    @Override
    public List<TerminalDTO> fetchFTRTerminals() {
        List<Terminal> terminals = terminalRepository.findAll();
        if (terminals.isEmpty()) {
            throw new RuntimeException("No terminals exist, please add.");
        }
        //return terminals.stream().map(this::convertToDTO).collect(Collectors.toList());
        List<TerminalDTO> list=new ArrayList<>();
        for(Terminal tm:terminals){
            list.add(convertToDTO(tm));
        }
        return list;
    }
    @Override
    public TerminalDTO fetchFTRTerminalByTerminalId(String terminalId) throws TerminalException {
        Terminal terminal = terminalRepository.findById(terminalId)
                .orElseThrow(() -> new TerminalException("Terminal details not found for ID : " + terminalId));
        return convertToDTO(terminal);
    }
    @Override
    public List<TerminalDTO> fetchTerminalsByItemType(String itemType) throws TerminalException {
        List<Terminal> terminals = terminalRepository.findByItemType(itemType);
        if (terminals.isEmpty()) {
            throw new TerminalException("No such Item type exists.");
        }
        return terminals.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
    @Override
    public TerminalDTO insertNewTerminal(TerminalDTO terminalDTO) {
        Terminal terminal = convertToEntity(terminalDTO);
        Terminal savedTerminal = terminalRepository.save(terminal);
        return convertToDTO(savedTerminal);
    }
    @Override
    public String updateTerminal(String terminalId, Integer newCapacity) {
        Terminal terminal = terminalRepository.findById(terminalId)
                .orElseThrow(() -> new RuntimeException("Terminal details not found for ID : " + terminalId));
        if (newCapacity > terminal.getAvailableCapacity()) {
            throw new RuntimeException("Given capacity is more or equal to the given capacity");
        }
        terminal.setAvailableCapacity(newCapacity);
        if (newCapacity.equals(terminal.getCapacity())) {
            terminal.setStatus("Not Available");
        }
        terminalRepository.save(terminal);
        return "Terminal capacity is successfully reduced for ID : " + terminalId;
    }
    @Override
    public String removeTerminal(String terminalId) {
        Terminal terminal = terminalRepository.findById(terminalId)
                .orElseThrow(() -> new RuntimeException("Terminal details not found for ID : " + terminalId));
        terminalRepository.delete(terminal);
        return "Terminal details are deleted successfully.";
    }

    private TerminalDTO convertToDTO(Terminal terminal) {
        TerminalDTO dto = new TerminalDTO();
        dto.setTerminalId(terminal.getTerminalId());
        dto.setTerminalName(terminal.getTerminalName());
        dto.setCountry(terminal.getCountry());
        dto.setItemType(terminal.getItemType());
        dto.setTerminalDescription(terminal.getTerminalDescription());
        dto.setCapacity(terminal.getCapacity());
        dto.setAvailableCapacity(terminal.getAvailableCapacity());
        dto.setStatus(terminal.getStatus());
        dto.setHarborLocation(terminal.getHarborLocation());
        return dto;
    }

    private Terminal convertToEntity(TerminalDTO dto) {
        Terminal terminal = new Terminal();
        terminal.setTerminalId(dto.getTerminalId());
        terminal.setTerminalName(dto.getTerminalName());
        terminal.setCountry(dto.getCountry());
        terminal.setItemType(dto.getItemType());
        terminal.setTerminalDescription(dto.getTerminalDescription());
        terminal.setCapacity(dto.getCapacity());
        terminal.setAvailableCapacity(dto.getAvailableCapacity());
        terminal.setStatus(dto.getStatus());
        terminal.setHarborLocation(dto.getHarborLocation());
        return terminal;
    }

}
