package com.killuacode.Todoapi.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class ErrorResponse {

    private String error;
    private int status;
    private List<String> messages;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:SS")
    private LocalDateTime timestamp;

    private String method;
    private String path;

}
