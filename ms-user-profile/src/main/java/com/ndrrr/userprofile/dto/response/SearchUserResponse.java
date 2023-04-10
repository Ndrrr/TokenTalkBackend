package com.ndrrr.userprofile.dto.response;

import com.ndrrr.userprofile.dto.UserProfileDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class SearchUserResponse {

    private List<UserProfileDto> users;

}
