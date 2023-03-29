package com.stickeralbum.clients.service.Impl;

import com.stickeralbum.clients.model.dto.AddressDTO;
import com.stickeralbum.clients.model.entity.Address;
import com.stickeralbum.clients.service.ViaCepClient;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import org.springframework.stereotype.Service;

@Service
public class ViaCepClientImpl implements ViaCepClient {

    private ViaCepClient client;

    public ViaCepClientImpl() {
        client = Feign.builder()
                .decoder(new JacksonDecoder())
                .target(ViaCepClient.class, "https://viacep.com.br/ws");
    }

    @Override
    public Address findAddressByCep(String cep) {
        return client.findAddressByCep(cep);
    }
}
