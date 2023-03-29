package com.stickeralbum.clients.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ClientDTO {
    private Integer id;
    private String name;
    private Date  birthDate;
    private String email;
    private String cpf;
    private AddressDTO address;
}
