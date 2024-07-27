package com.transport.service;

import com.transport.dto.TerminalDTO;

import java.util.List;

public interface TerminalService {
    public List<TerminalDTO> fetchFTRTerminals();
    public TerminalDTO fetchFTRTerminalByTerminalId(String terminalId);
    public List<TerminalDTO> fetchTerminalsByItemType(String itemType);
    public TerminalDTO insertNewTerminal(TerminalDTO terminalDTO);
    public String updateTerminal(String terminalId, Integer newCapacity);
    public String removeTerminal(String terminalId);
}
