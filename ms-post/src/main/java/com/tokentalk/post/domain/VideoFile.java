package com.tokentalk.post.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.InputStream;

@Data
@EqualsAndHashCode(callSuper = true)
public class VideoFile extends BaseFile{

    private InputStream stream;

}
