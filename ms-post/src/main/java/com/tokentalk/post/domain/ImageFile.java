package com.tokentalk.post.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.bson.types.Binary;

@Data
@EqualsAndHashCode(callSuper = true)
public class ImageFile extends BaseFile{

    private Binary image;

}
