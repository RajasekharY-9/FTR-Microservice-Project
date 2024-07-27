package com.transport.service;

import com.transport.dto.TerminalDTO;
import com.transport.exception.TerminalException;

import java.util.List;

public interface TerminalService {
    public List<TerminalDTO> fetchFTRTerminals();
    public TerminalDTO fetchFTRTerminalByTerminalId(String terminalId) throws TerminalException;
    public List<TerminalDTO> fetchTerminalsByItemType(String itemType) throws TerminalException;
    public TerminalDTO insertNewTerminal(TerminalDTO terminalDTO);
    public String updateTerminal(String terminalId, Integer newCapacity);
    public String removeTerminal(String terminalId);
}
