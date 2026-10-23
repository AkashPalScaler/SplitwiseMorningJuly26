package com.scaler.splitwisemorningjuly26.Controllers;

import com.scaler.splitwisemorningjuly26.DTO.*;
import com.scaler.splitwisemorningjuly26.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    public UserRegistratonResponseDTO register(UserRegistrationRequestDTO requestDTO){
        return null;
    }

    public SettleUpUserResponseDTO settleUp(SettleUpUserRequestDTO requestDTO){
        SettleUpUserResponseDTO responseDTO = new SettleUpUserResponseDTO();
        try{
            List<Transaction> transactions = userService.settleUp(requestDTO.getUserId());
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

// Controller, Services, Repository