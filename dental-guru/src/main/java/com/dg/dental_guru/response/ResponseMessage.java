package com.dg.dental_guru.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
        String message;
        Boolean success;

        public boolean isSuccess() {
            return success;
        }
}
