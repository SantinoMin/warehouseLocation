package warehouseLocation.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@ToString
@AllArgsConstructor
public class AreaResDto {

    @NotNull(message = "AreaId is required.")
    private Long areaId;

    @NotBlank(message = "areaName is required.")
    private String areaName;

    @NotBlank(message = "createdAt is required.")
    LocalDateTime createdAt;

    @NotNull(message = "status is required.")
    private int status;


    public AreaResDto() {

    }

//  @NotBlank(message = "createdAt is required.")
//  LocalDateTime createdAt;

    @Data
    public static class Message {

        String message;

        public Message(String message) {
            this.message = message;
        }
    }


};