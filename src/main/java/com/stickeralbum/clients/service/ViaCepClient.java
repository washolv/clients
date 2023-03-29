package com.stickeralbum.clients.service;

import com.stickeralbum.clients.model.entity.Address;
import feign.Param;
import feign.RequestLine;

public interface ViaCepClient {

    @RequestLine("GET /{cep}/json/")
    Address findAddressByCep(@Param("cep") String cep);

}
