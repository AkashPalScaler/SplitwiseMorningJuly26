package com.scaler.splitwisemorningjuly26.Commands;

import com.scaler.splitwisemorningjuly26.Controllers.GroupController;
import com.scaler.splitwisemorningjuly26.DTO.ResponseStatus;
import com.scaler.splitwisemorningjuly26.DTO.SettleUpGroupRequestDTO;
import com.scaler.splitwisemorningjuly26.DTO.SettleUpGroupResponseDTO;
import com.scaler.splitwisemorningjuly26.DTO.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
@Component
public class SettleGroup implements Command {
    @Autowired
    GroupController groupController;
    @Override
    public boolean matchCommand(String input) {
        String command = Arrays.asList(input.split(" ")).get(0);
        return command.equalsIgnoreCase("settlegroup");
    }

    @Override
    public void execute(String input) {
        Long groupId = Long.parseLong(Arrays.asList(input.split(" ")).get(1));
        SettleUpGroupRequestDTO settleUpGroupRequestDTO = new SettleUpGroupRequestDTO();
        settleUpGroupRequestDTO.setGroupId(groupId);
        SettleUpGroupResponseDTO responseDTO = groupController.settleUpGroup(settleUpGroupRequestDTO);
        if(responseDTO.getStatus().equals(ResponseStatus.SUCCESS)){
            System.out.println("Please clear the following txns to settle : ");
            for(Transaction transaction : responseDTO.getTransactions()){
                System.out.println(transaction.getFromUser()  + " needs to pay " +  transaction.getAmount() +" to " + transaction.getToUser());
            }
        }
    }
}
