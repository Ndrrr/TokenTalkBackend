package com.tokentalk.nft.service;

import com.tokentalk.nft.client.FileResponse;
import com.tokentalk.nft.client.PostClient;
import com.tokentalk.nft.dto.request.UploadToInfuraRequest;
import com.tokentalk.nft.dto.response.UploadToInfuraResponse;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class NftService {

    private final PostClient postClient;

    public UploadToInfuraResponse uploadToInfura(UploadToInfuraRequest request) {
        IPFS ipfs = new IPFS("/ndrrr/ipfs.infura.io/tcp/5001/https");
        FileResponse fileResponse = postClient.getFile(request.getFileId());
        try {
            NamedStreamable.InputStreamWrapper is =
                    new NamedStreamable.InputStreamWrapper(fileResponse.getFile().getInputStream());
            MerkleNode response = ipfs.add(is).get(0);
        } catch (IOException ex) {
            throw new RuntimeException("Error whilst communicating with the IPFS node", ex);
        }
        return new UploadToInfuraResponse();
    }

}
