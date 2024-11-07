package com.party.Party.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.party.Party.dto.ProfileDto;
import com.party.Party.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class, CommentMapper.class, UserMapper.class})
public interface ProfileMapper {
    @Mapping(target = "addressDto", source = "address")
    @Mapping(target = "userDto", source = "user")
    @Mapping(target = "commentDtos", source = "comments")
    @Mapping(target = "commentsWriteDto", source = "commentsWrite")
    @Mapping(target = "interests", expression = "java(mapJsonNodeToList(profile.getInterests()))")
    ProfileDto toDto(Profile profile);

    @Mapping(target = "address", source = "addressDto")
    @Mapping(target = "user", source = "userDto")
    @Mapping(target = "comments", source = "commentDtos")
    @Mapping(target = "commentsWrite", source = "commentsWriteDto")
    @Mapping(target = "interests", expression = "java(mapListToJsonNode(profileDto.getInterests()))")
    Profile toEntity(ProfileDto profileDto);

    List<ProfileDto> toDtos(List<Profile> profiles);
    List<Profile> toEntities(List<ProfileDto> profileDtos);

    default List<String> mapJsonNodeToList(String jsonAsString) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(jsonAsString);
            if (jsonNode == null || jsonNode.isNull() || !jsonNode.isArray()) {
                return new ArrayList<>();
            }
            List<String> interests = new ArrayList<>();
            jsonNode.forEach(node -> interests.add(node.asText()));
            return interests;
        }
        catch(Exception e) {
            return List.of();
        }
    }

    default String mapListToJsonNode(List<String> interests) {
        ObjectMapper mapper = new ObjectMapper();
        return interests != null ? mapper.valueToTree(interests).toString() : null;
    }
}
