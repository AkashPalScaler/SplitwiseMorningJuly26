package com.scaler.splitwisemorningjuly26.Controllers;

import com.scaler.splitwisemorningjuly26.DTO.ResponseStatus;
import com.scaler.splitwisemorningjuly26.DTO.SettleUpGroupRequestDTO;
import com.scaler.splitwisemorningjuly26.DTO.SettleUpGroupResponseDTO;
import com.scaler.splitwisemorningjuly26.DTO.Transaction;
import com.scaler.splitwisemorningjuly26.Services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class GroupController {
    @Autowired
    private GroupService groupService;

    public SettleUpGroupResponseDTO settleUpGroup(SettleUpGroupRequestDTO requestDTO){
        SettleUpGroupResponseDTO responseDTO = new SettleUpGroupResponseDTO();
        try{
            List<Transaction> transactions = groupService.settleUpGroup(requestDTO.getGroupId());
            responseDTO.setTransactions(transactions);
            responseDTO.setMessage("Clear the following transactions to settle up the group");
            responseDTO.setStatus(ResponseStatus.SUCCESS);

        }catch (Exception e){
            responseDTO.setStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage("Failure to calculate transactions for settlement - " + e.getMessage());

        }
        return responseDTO;
    }
}
