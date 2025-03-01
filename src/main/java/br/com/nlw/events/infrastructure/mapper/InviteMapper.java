package br.com.nlw.events.infrastructure.mapper;

import br.com.nlw.events.domain.models.Invite;
import br.com.nlw.events.infrastructure.entity.InviteEntity;
import org.springframework.stereotype.Component;

@Component
public class InviteMapper {

    private final EventMapper eventMapper;
    private final UserMapper userMapper;

    public InviteMapper(EventMapper eventMapper, UserMapper userMapper) {
        this.eventMapper = eventMapper;
        this.userMapper = userMapper;
    }

    public Invite toDomain(InviteEntity entity) {
        if (entity == null) return null;
        return new Invite(
                entity.getId(),
                entity.getHits(),
                eventMapper.toDomain(entity.getEvent()),
                userMapper.toDomain(entity.getSubscriber())
        );
    }

    public InviteEntity toEntity(Invite invite) {
        if (invite == null) return null;
        return new InviteEntity(
                invite.getId(),
                invite.getHits(),
                eventMapper.toEntity(invite.getEvent()),
                userMapper.toEntity(invite.getSubscriber())
        );
    }
}