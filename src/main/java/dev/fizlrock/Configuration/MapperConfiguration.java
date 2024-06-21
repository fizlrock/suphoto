package dev.fizlrock.Configuration;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.openapitools.model.EventDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.fizlrock.Domain.Event;

/**
 * MapperConfig
 */
@Configuration
public class MapperConfiguration {

  @Bean
  public ModelMapper mapper() {
    ModelMapper mm = new ModelMapper();
    TypeMap<EventDTO, Event> pm = mm.createTypeMap(EventDTO.class, Event.class);

    pm.addMapping(EventDTO::getTitle, Event::setTitle);
    pm.addMapping(EventDTO::getLocation, Event::setLocation);

    return mm;
  }
}
