package warehouseLocation.domain.dto;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Message {

    private Long areaId;
    private Long rackNumber;

    private String areaName;

    private Long isValid;
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }


};