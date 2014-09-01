CREATE TABLE `ares` (
  `ar_id` int(6) NOT NULL AUTO_INCREMENT,
  `ar_number` varchar(4) NOT NULL,
  `business_context_id` int(4) NOT NULL,
  PRIMARY KEY (`ar_id`),
  UNIQUE KEY `unique_nr_ar` (`business_context_id`,`ar_number`),
  KEY `fk_ares_business_contexts1_idx` (`business_context_id`),
  CONSTRAINT `fk_ares_business_contexts1` FOREIGN KEY (`business_context_id`) REFERENCES `business_contexts` (`business_context_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `ares_photos` (
  `ares_ar_id` int(6) NOT NULL,
  `photos_photo_id` int(8) NOT NULL,
  PRIMARY KEY (`ares_ar_id`,`photos_photo_id`),
  KEY `ares_photos_photo_id_idx` (`photos_photo_id`),
  CONSTRAINT `ares_photos_are_id` FOREIGN KEY (`ares_ar_id`) REFERENCES `ares` (`ar_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `ares_photos_photo_id` FOREIGN KEY (`photos_photo_id`) REFERENCES `photos` (`photo_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `business_contexts` (
  `business_context_id` int(4) NOT NULL AUTO_INCREMENT,
  `business_context_city` varchar(25) NOT NULL,
  `business_context_municipality` varchar(30) NOT NULL,
  `business_context_voivodeship` varchar(25) NOT NULL,
  `business_context_azp_area_nr` varchar(6) NOT NULL,
  `business_context_nr_in_city` int(3) NOT NULL,
  `business_context_site_nr` int(3) NOT NULL,
  PRIMARY KEY (`business_context_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `chronologies` (
  `chronology_id` int(3) NOT NULL AUTO_INCREMENT,
  `chronology_name` varchar(20) NOT NULL,
  PRIMARY KEY (`chronology_id`),
  UNIQUE KEY `chronology_name_UNIQUE` (`chronology_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `culture` (
  `culture_id` int(3) NOT NULL AUTO_INCREMENT,
  `culture_name` varchar(20) NOT NULL,
  PRIMARY KEY (`culture_id`),
  UNIQUE KEY `culture_name_UNIQUE` (`culture_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `figure_specifications` (
  `figure_specification_id` int(6) NOT NULL AUTO_INCREMENT,
  `figure_specification_profile` tinyint(1) NOT NULL,
  `figure_specification_projection` tinyint(1) NOT NULL,
  `figure_id` int(6) NOT NULL,
  `object_id` int(6) NOT NULL,
  PRIMARY KEY (`figure_specification_id`,`figure_id`),
  UNIQUE KEY `figure_spec_object_id_unique` (`object_id`,`figure_id`),
  KEY `fk_picture_specifications_pictures1_idx` (`figure_id`),
  KEY `fk_picture_specifications_objects1_idx` (`object_id`),
  CONSTRAINT `fk_figure_id` FOREIGN KEY (`figure_id`) REFERENCES `figures` (`figure_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_figure_object_id` FOREIGN KEY (`object_id`) REFERENCES `objects` (`object_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

CREATE TABLE `figure_subjects` (
  `figure_subject_id` int(3) NOT NULL AUTO_INCREMENT,
  `figure_subject_name` varchar(20) NOT NULL,
  PRIMARY KEY (`figure_subject_id`),
  UNIQUE KEY `picture_subject_name_UNIQUE` (`figure_subject_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `figures` (
  `figure_id` int(6) NOT NULL AUTO_INCREMENT,
  `figure_nr` varchar(4) NOT NULL,
  `figure_date` date NOT NULL,
  `figure_subject_id` int(3) NOT NULL,
  `business_context_id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL,
  PRIMARY KEY (`figure_id`),
  UNIQUE KEY `unique_picture_nr` (`business_context_id`,`figure_nr`),
  KEY `fk_pictures_picture_subjects1_idx` (`figure_subject_id`),
  KEY `fk_pictures_business_contexts1_idx` (`business_context_id`),
  KEY `fk_pictures_users1_idx` (`user_id`),
  CONSTRAINT `fk_figure_subject_id` FOREIGN KEY (`figure_subject_id`) REFERENCES `figure_subjects` (`figure_subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pictures_business_contexts1` FOREIGN KEY (`business_context_id`) REFERENCES `business_contexts` (`business_context_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pictures_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `functions` (
  `function_id` int(3) NOT NULL AUTO_INCREMENT,
  `function_name` varchar(25) NOT NULL,
  PRIMARY KEY (`function_id`),
  UNIQUE KEY `function_name_UNIQUE` (`function_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `mass_relics` (
  `mass_relic_id` int(6) NOT NULL AUTO_INCREMENT,
  `mass_relic_nr` varchar(5) NOT NULL,
  `mass_relic_depth` decimal(4,2) NOT NULL,
  `mass_relic_exploration_date` date NOT NULL,
  `object_id` int(6) NOT NULL,
  `ar_id` int(6) NOT NULL,
  `business_context_id` int(4) NOT NULL,
  PRIMARY KEY (`mass_relic_id`),
  UNIQUE KEY `unique_nr_mass_relic` (`business_context_id`,`mass_relic_nr`),
  KEY `fk_mass_relics_objects1_idx` (`object_id`),
  KEY `fk_mass_relics_ares1_idx` (`ar_id`),
  KEY `fk_mass_relics_business_contexts1_idx` (`business_context_id`),
  CONSTRAINT `fk_mass_relics_ares1` FOREIGN KEY (`ar_id`) REFERENCES `ares` (`ar_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mass_relics_business_contexts1` FOREIGN KEY (`business_context_id`) REFERENCES `business_contexts` (`business_context_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_mass_relics_objects1` FOREIGN KEY (`object_id`) REFERENCES `objects` (`object_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `objects` (
  `object_id` int(6) NOT NULL AUTO_INCREMENT,
  `object_nr` varchar(45) NOT NULL,
  `object_leveling_min` decimal(2,2) DEFAULT NULL,
  `object_elevation_from_surface` decimal(2,2) DEFAULT NULL,
  `object_exploration_date` date NOT NULL,
  `object_fill_date` date DEFAULT NULL,
  `object_projection_x` decimal(2,2) DEFAULT NULL,
  `object_projection_y` decimal(2,2) DEFAULT NULL,
  `object_elevation` decimal(4,2) DEFAULT NULL,
  `business_context_id` int(4) NOT NULL,
  `user_id` int(4) DEFAULT NULL,
  `profile_shape_id` int(3) DEFAULT NULL,
  `projection_shape_id` int(3) DEFAULT NULL,
  `function_id` int(3) DEFAULT NULL,
  `culture_id` int(3) DEFAULT NULL,
  `soil_id` int(3) DEFAULT NULL,
  `chronology_id` int(3) DEFAULT NULL,
  PRIMARY KEY (`object_id`),
  UNIQUE KEY `unique_nr_object` (`business_context_id`,`object_nr`),
  KEY `fk_objects_business_contexts1_idx` (`business_context_id`),
  KEY `fk_objects_users1_idx` (`user_id`),
  KEY `fk_objects_profile_shapes1_idx` (`profile_shape_id`),
  KEY `fk_objects_projection_shapes1_idx` (`projection_shape_id`),
  KEY `fk_objects_functions1_idx` (`function_id`),
  KEY `fk_objects_culture1_idx` (`culture_id`),
  KEY `fk_objects_soils1_idx` (`soil_id`),
  KEY `fk_objects_chronologies1_idx` (`chronology_id`),
  CONSTRAINT `fk_objects_business_contexts1` FOREIGN KEY (`business_context_id`) REFERENCES `business_contexts` (`business_context_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_objects_chronologies1` FOREIGN KEY (`chronology_id`) REFERENCES `chronologies` (`chronology_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_objects_culture1` FOREIGN KEY (`culture_id`) REFERENCES `culture` (`culture_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_objects_functions1` FOREIGN KEY (`function_id`) REFERENCES `functions` (`function_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_objects_profile_shapes1` FOREIGN KEY (`profile_shape_id`) REFERENCES `profile_shapes` (`profile_shape_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_objects_projection_shapes1` FOREIGN KEY (`projection_shape_id`) REFERENCES `projection_shapes` (`projection_shape_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_objects_soils1` FOREIGN KEY (`soil_id`) REFERENCES `soils` (`soil_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_objects_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `objects_ares` (
  `object_id` int(6) NOT NULL,
  `ar_id` int(6) NOT NULL,
  PRIMARY KEY (`object_id`,`ar_id`),
  KEY `fk_objects_has_ares_ares1_idx` (`ar_id`),
  KEY `fk_objects_has_ares_objects1_idx` (`object_id`),
  CONSTRAINT `fk_objects_has_ares_ares1` FOREIGN KEY (`ar_id`) REFERENCES `ares` (`ar_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_objects_has_ares_objects1` FOREIGN KEY (`object_id`) REFERENCES `objects` (`object_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `objects_profile_layers` (
  `object_id` int(6) NOT NULL,
  `profile_layer_id` int(3) NOT NULL,
  PRIMARY KEY (`object_id`,`profile_layer_id`),
  KEY `fk_objects_has_profile_layers_profile_layers1_idx` (`profile_layer_id`),
  KEY `fk_objects_has_profile_layers_objects1_idx` (`object_id`),
  CONSTRAINT `fk_objects_has_profile_layers_objects1` FOREIGN KEY (`object_id`) REFERENCES `objects` (`object_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_objects_has_profile_layers_profile_layers1` FOREIGN KEY (`profile_layer_id`) REFERENCES `profile_layers` (`profile_layer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `objects_projection_layers` (
  `object_id` int(6) NOT NULL,
  `projection_layer_id` int(3) NOT NULL,
  PRIMARY KEY (`object_id`,`projection_layer_id`),
  KEY `fk_objects_has_projection_layers_projection_layers1_idx` (`projection_layer_id`),
  KEY `fk_objects_has_projection_layers_objects1_idx` (`object_id`),
  CONSTRAINT `fk_objects_has_projection_layers_objects1` FOREIGN KEY (`object_id`) REFERENCES `objects` (`object_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_objects_has_projection_layers_projection_layers1` FOREIGN KEY (`projection_layer_id`) REFERENCES `projection_layers` (`projection_layer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `photo_subjects` (
  `photo_subject_id` int(4) NOT NULL AUTO_INCREMENT,
  `photo_subject_name` varchar(20) NOT NULL,
  PRIMARY KEY (`photo_subject_id`),
  UNIQUE KEY `photo_subject_name_UNIQUE` (`photo_subject_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `photos` (
  `photo_id` int(8) NOT NULL AUTO_INCREMENT,
  `photo_nr` int(5) NOT NULL,
  `photo_exploration_date` date NOT NULL,
  `photo_subject_id` int(4) NOT NULL,
  `user_id` int(4) NOT NULL,
  `object_id` int(6) DEFAULT NULL,
  `business_context_id` int(4) NOT NULL,
  PRIMARY KEY (`photo_id`),
  UNIQUE KEY `UNIQUE_PHOTO_NR` (`business_context_id`,`photo_nr`),
  KEY `fk_photos_photo_subjects1_idx` (`photo_subject_id`),
  KEY `fk_photos_users1_idx` (`user_id`),
  KEY `fk_photos_objects1_idx` (`object_id`),
  KEY `fk_photos_business_contexts1_idx` (`business_context_id`),
  CONSTRAINT `fk_photos_business_contexts1` FOREIGN KEY (`business_context_id`) REFERENCES `business_contexts` (`business_context_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_photos_objects1` FOREIGN KEY (`object_id`) REFERENCES `objects` (`object_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_photos_photo_subjects1` FOREIGN KEY (`photo_subject_id`) REFERENCES `photo_subjects` (`photo_subject_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_photos_users1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `profile_layers` (
  `profile_layer_id` int(3) NOT NULL AUTO_INCREMENT,
  `profile_layer_name` varchar(20) NOT NULL,
  `profile_layer_structure` char(1) NOT NULL,
  PRIMARY KEY (`profile_layer_id`),
  UNIQUE KEY `profile_layer_name_UNIQUE` (`profile_layer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `profile_shapes` (
  `profile_shape_id` int(3) NOT NULL AUTO_INCREMENT,
  `profile_shape_name` varchar(30) NOT NULL,
  PRIMARY KEY (`profile_shape_id`),
  UNIQUE KEY `profile_shape_name_UNIQUE` (`profile_shape_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `projection_layers` (
  `projection_layer_id` int(3) NOT NULL,
  `projection_layer_name` varchar(20) NOT NULL,
  `projection_layer_structure` char(1) NOT NULL,
  PRIMARY KEY (`projection_layer_id`),
  UNIQUE KEY `projection_layer_name_UNIQUE` (`projection_layer_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `projection_shapes` (
  `projection_shape_id` int(3) NOT NULL AUTO_INCREMENT,
  `projection_shape_name` varchar(30) NOT NULL,
  PRIMARY KEY (`projection_shape_id`),
  UNIQUE KEY `projection_shape_name_UNIQUE` (`projection_shape_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `relic_groups` (
  `relic_group_id` int(3) NOT NULL AUTO_INCREMENT,
  `relic_group_name` varchar(20) NOT NULL,
  PRIMARY KEY (`relic_group_id`),
  UNIQUE KEY `relic_group_name_UNIQUE` (`relic_group_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `relic_layers` (
  `relic_layer_id` int(3) NOT NULL AUTO_INCREMENT,
  `relic_layer_name` varchar(20) NOT NULL,
  `mass_relic_id` int(6) NOT NULL,
  PRIMARY KEY (`relic_layer_id`),
  UNIQUE KEY `relic_layer_name_UNIQUE` (`relic_layer_name`),
  KEY `fk_relic_layers_mass_relics1_idx` (`mass_relic_id`),
  CONSTRAINT `fk_relic_layers_mass_relics1` FOREIGN KEY (`mass_relic_id`) REFERENCES `mass_relics` (`mass_relic_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `relic_specyfications` (
  `relic_specyfication_id` int(6) NOT NULL AUTO_INCREMENT,
  `relic_specyfication_count` int(3) NOT NULL,
  `mass_relics_mass_relic_id` int(6) NOT NULL,
  `relic_type_id` int(3) NOT NULL,
  `chronology_id` int(3) NOT NULL,
  PRIMARY KEY (`relic_specyfication_id`,`mass_relics_mass_relic_id`),
  KEY `fk_relic_specyfications_mass_relics1_idx` (`mass_relics_mass_relic_id`),
  KEY `fk_relic_specyfications_relic_types1_idx` (`relic_type_id`),
  KEY `fk_relic_specyfications_chronologies1_idx` (`chronology_id`),
  CONSTRAINT `fk_relic_specyfications_chronologies1` FOREIGN KEY (`chronology_id`) REFERENCES `chronologies` (`chronology_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_relic_specyfications_mass_relics1` FOREIGN KEY (`mass_relics_mass_relic_id`) REFERENCES `mass_relics` (`mass_relic_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_relic_specyfications_relic_types1` FOREIGN KEY (`relic_type_id`) REFERENCES `relic_types` (`relic_type_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `relic_types` (
  `relic_type_id` int(3) NOT NULL AUTO_INCREMENT,
  `relic_type_name` varchar(20) NOT NULL,
  `relic_group_id` int(3) NOT NULL,
  PRIMARY KEY (`relic_type_id`),
  UNIQUE KEY `relic_type_name_UNIQUE` (`relic_type_name`),
  KEY `fk_relic_types_relic_groups1_idx` (`relic_group_id`),
  CONSTRAINT `fk_relic_types_relic_groups1` FOREIGN KEY (`relic_group_id`) REFERENCES `relic_groups` (`relic_group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `separated_relics` (
  `separated_relic_id` int(6) NOT NULL AUTO_INCREMENT,
  `separated_relic_nr` varchar(6) NOT NULL,
  `separated_relic_depth` decimal(4,2) NOT NULL,
  `separated_relic_exploration_date` date NOT NULL,
  `details` varchar(300) NOT NULL,
  `relic_layer_id` int(3) NOT NULL,
  `object_id` int(6) NOT NULL,
  `chronology_id` int(3) NOT NULL,
  `relic_group_id` int(3) NOT NULL,
  `ar_id` int(6) NOT NULL,
  `context_id` int(4) NOT NULL,
  PRIMARY KEY (`separated_relic_id`),
  UNIQUE KEY `unique_nr_separated_relics` (`context_id`,`separated_relic_nr`),
  KEY `fk_separated_relics_relic_layers1_idx` (`relic_layer_id`),
  KEY `fk_separated_relics_objects1_idx` (`object_id`),
  KEY `fk_separated_relics_chronologies1_idx` (`chronology_id`),
  KEY `fk_separated_relics_relic_groups1_idx` (`relic_group_id`),
  KEY `fk_separated_relics_ares1_idx` (`ar_id`),
  KEY `fk_separated_relics_business_contexts1_idx` (`context_id`),
  CONSTRAINT `fk_separated_relics_ares1` FOREIGN KEY (`ar_id`) REFERENCES `ares` (`ar_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_separated_relics_business_contexts1` FOREIGN KEY (`context_id`) REFERENCES `business_contexts` (`business_context_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_separated_relics_chronologies1` FOREIGN KEY (`chronology_id`) REFERENCES `chronologies` (`chronology_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_separated_relics_objects1` FOREIGN KEY (`object_id`) REFERENCES `objects` (`object_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_separated_relics_relic_groups1` FOREIGN KEY (`relic_group_id`) REFERENCES `relic_groups` (`relic_group_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_separated_relics_relic_layers1` FOREIGN KEY (`relic_layer_id`) REFERENCES `relic_layers` (`relic_layer_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `soils` (
  `soil_id` int(3) NOT NULL AUTO_INCREMENT,
  `soil_name` varchar(30) NOT NULL,
  PRIMARY KEY (`soil_id`),
  UNIQUE KEY `soil_name_UNIQUE` (`soil_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `user_id` int(4) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(20) NOT NULL,
  `user_lastname` varchar(30) NOT NULL,
  `user_login` varchar(20) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_login_UNIQUE` (`user_login`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
