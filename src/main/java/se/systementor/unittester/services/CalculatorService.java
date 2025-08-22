package se.systementor.unittester.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.systementor.unittester.data.LogUsageRecord;
import se.systementor.unittester.data.LogUsageRecordRepository;

@Service
public class CalculatorService {

    @Autowired
    private LogUsageRecordRepository logUsageRecordRepository;


    public int Calculate(int tal1, int tal2, String action) {

        int result = 0;
        if (action.equals("add")) {
            result =  tal1 + tal2;
        }
        else if (action.equals("subtract")) {
            result =  tal1 - tal2;
        }
        else if (action.equals("multiply")) {
            result = tal1 * tal2;
        }
        else if (action.equals("divide")) {
            result = tal1 / tal2;
        }
        else {
            throw new IllegalArgumentException("Invalid action");
        }
        logUsageRecordRepository.save(
                new LogUsageRecord(tal1,tal2,action,result));
        return result;
    }
}
