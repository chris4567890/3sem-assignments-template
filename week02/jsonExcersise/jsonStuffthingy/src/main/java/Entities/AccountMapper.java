package Entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.example.AccountDTO;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class AccountMapper {

    public AccountMapper(){

    }



    public List<AccountDTO> getListofAccountDtos(){
        List<AccountDTO> accountDTOS = new ArrayList<>();
        ObjectMapper om = new ObjectMapper();
        Gson gson = new Gson();

        String filename = "C:/Users/Christian Høj/Desktop/3sem-assignments-template/week02/jsonExcersise/jsonStuffthingy/src/main/java/org/example/account.json";


        try{
            FileReader fileReader = new FileReader(filename);
            //based on the code these websites: https://howtodoinjava.com/gson/gson-jsonparser/ https://www.w3docs.com/snippets/java/gson-throwing-expected-begin-object-but-was-begin-array.html https://www.baeldung.com/gson-parsing-errors
            JsonElement jsonElement = JsonParser.parseReader(fileReader);
            AccountDTO[] acc = gson.fromJson(jsonElement, AccountDTO[].class);
            for (AccountDTO accountDTO : acc){
                accountDTOS.add(accountDTO);
            }
            return accountDTOS;
        }catch(IOException e){
            throw new RuntimeException(e);
        }



    }





}