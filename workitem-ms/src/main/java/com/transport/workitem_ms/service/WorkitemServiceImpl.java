package com.transport.workitem_ms.service;

import com.transport.workitem_ms.dto.WorkitemDTO;
import com.transport.workitem_ms.entity.Workitem;
import com.transport.workitem_ms.exception.WorkitemNotFoundException;
import com.transport.workitem_ms.repository.WorkitemRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkitemServiceImpl implements WorkItemService{
    @Autowired
    private WorkitemRepository workitemRepository;

    public WorkitemDTO createWorkitem(@Valid WorkitemDTO workitemDto) {
        // Generate workitemId and calculate amount based on itemType
        String workitemId = "J" + (int) (Math.random() * 1000000);
        workitemDto.setWorkitemId(workitemId);
        workitemDto.setAmount(calculateAmount(workitemDto));
        Workitem workitem = convertToEntity(workitemDto);
        workitemRepository.save(workitem);
        return workitemDto;
    }

    public void updateWorkitemId(String workitemId, @Valid WorkitemDTO workitemDto) {
        Workitem workitem = workitemRepository.findById(workitemId)
                .orElseThrow(() -> new WorkitemNotFoundException("Workitem not found with id " + workitemId));
        // Update workitem fields
        workitem.setItemName(workitemDto.getItemName());
        workitem.setItemType(workitemDto.getItemType());
        workitem.setItemDescription(workitemDto.getItemDescription());
        workitem.setMessageToRecipient(workitemDto.getMessageToRecipient());
        workitem.setQuantity(workitemDto.getQuantity());
        workitem.setSourceCountry(workitemDto.getSourceCountry());
        workitem.setDestinationCountry(workitemDto.getDestinationCountry());
        workitem.setOriginHarborLocation(workitemDto.getOriginHarborLocation());
        workitem.setSelectedHarborLocation(workitemDto.getSelectedHarborLocation());
        workitem.setShippingDate(workitemDto.getShippingDate());
        workitem.setAmount(workitemDto.getAmount());
        workitemRepository.save(workitem);
    }

/*
    public List<String> fetchAvailableHarborLocations(String fromCountry) {
        return workitemRepository.findAvailableHarborLocations(fromCountry);
    }
*/

    public List<WorkitemDTO> fetchWorkItemDetails() {
        List<Workitem> workitems = workitemRepository.findAll();
        return convertToDTOList(workitems);
    }

    public List<WorkitemDTO> trackWorkitemByUser(Long userId) {
        List<Workitem> workitems = workitemRepository.findByUserId(userId);
        return convertToDTOList(workitems);
    }

    public WorkitemDTO fetchWorkItemStatus(String workitemId) {
        Workitem workitem = workitemRepository.findById(workitemId)
                .orElseThrow(() -> new WorkitemNotFoundException("Workitem not found with id " + workitemId));
        return convertToDTO(workitem);
    }

    public void updateWorkItemStatus(String workitemId) {
        Workitem workitem = workitemRepository.findById(workitemId)
                .orElseThrow(() -> new WorkitemNotFoundException("Workitem not found with id " + workitemId));
        // Update status (e.g., change a status field or similar)
        workitemRepository.save(workitem);
    }

    public void assignTerminalForWorkitem(String workitemId) {
        Workitem workitem = workitemRepository.findById(workitemId)
                .orElseThrow(() -> new WorkitemNotFoundException("Workitem not found with id " + workitemId));
        // Assign terminal logic (e.g., update a terminal field or similar)
        workitemRepository.save(workitem);
    }

  /*  public WorkitemDTO fetchWorkItemDetailsByVehicleNumber(String vehicleNumber) {
        Workitem workitem = workitemRepository.findByVehicleNumber(vehicleNumber)
                .orElseThrow(() -> new WorkitemNotFoundException("Workitem not found for vehicle number " + vehicleNumber));
        return convertToDTO(workitem);
    }
*/
    public void allocateVehicle(String workitemId) {
        Workitem workitem = workitemRepository.findById(workitemId)
                .orElseThrow(() -> new WorkitemNotFoundException("Workitem not found with id " + workitemId));
        // Allocate vehicle logic (e.g., update a vehicle field or similar)
        workitemRepository.save(workitem);
    }

    private Workitem convertToEntity(WorkitemDTO dto) {
        Workitem workitem = new Workitem();
        workitem.setWorkitemId(dto.getWorkitemId());
        workitem.setUserId(dto.getUserId());
        workitem.setItemName(dto.getItemName());
        workitem.setItemType(dto.getItemType());
        workitem.setItemDescription(dto.getItemDescription());
        workitem.setMessageToRecipient(dto.getMessageToRecipient());
        workitem.setQuantity(dto.getQuantity());
        workitem.setSourceCountry(dto.getSourceCountry());
        workitem.setDestinationCountry(dto.getDestinationCountry());
        workitem.setOriginHarborLocation(dto.getOriginHarborLocation());
        workitem.setSelectedHarborLocation(dto.getSelectedHarborLocation());
        workitem.setShippingDate(dto.getShippingDate());
        workitem.setAmount(dto.getAmount());
        return workitem;
    }

    private WorkitemDTO convertToDTO(Workitem workitem) {
        WorkitemDTO dto = new WorkitemDTO();
        dto.setWorkitemId(workitem.getWorkitemId());
        dto.setUserId(workitem.getUserId());
        dto.setItemName(workitem.getItemName());
        dto.setItemType(workitem.getItemType());
        dto.setItemDescription(workitem.getItemDescription());
        dto.setMessageToRecipient(workitem.getMessageToRecipient());
        dto.setQuantity(workitem.getQuantity());
        dto.setSourceCountry(workitem.getSourceCountry());
        dto.setDestinationCountry(workitem.getDestinationCountry());
        dto.setOriginHarborLocation(workitem.getOriginHarborLocation());
        dto.setSelectedHarborLocation(workitem.getSelectedHarborLocation());
        dto.setShippingDate(workitem.getShippingDate());
        dto.setAmount(workitem.getAmount());
        return dto;
    }

    private List<WorkitemDTO> convertToDTOList(List<Workitem> workitems) {
        return workitems.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private Long calculateAmount(WorkitemDTO dto) {
        // Sample calculation logic based on itemType
        switch (dto.getItemType().toLowerCase()) {
            case "electronics":
                return 100L * Long.parseLong(dto.getQuantity());
            case "furniture":
                return 50L * Long.parseLong(dto.getQuantity());
            case "clothing":
                return 20L * Long.parseLong(dto.getQuantity());
            default:
                return 10L * Long.parseLong(dto.getQuantity());
        }
    }
}
