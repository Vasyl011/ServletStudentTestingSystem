CREATE DATABASE `db_servlet`;
CREATE TABLE `db_servlet`.`roles` (
                                      `id` int(11) NOT NULL,
                                      `role` VARCHAR(255) NULL,
                                      PRIMARY KEY (`id`));
CREATE TABLE `db_servlet`.`usr` (
                                    `usr_id` int(11) NOT NULL AUTO_INCREMENT,
                                    `blocked` BIT(1) NOT NULL,
                                    `password` VARCHAR(255) NULL DEFAULT NULL,
                                    `username` VARCHAR(255) NULL DEFAULT NULL,
                                    `role_id` int(11) NOT NULL,
                                    PRIMARY KEY (`usr_id`));

CREATE TABLE `db_servlet`.`tests` (
                                      `test_id` int(11) NOT NULL AUTO_INCREMENT,
                                      `subject_name` varchar(255) DEFAULT NULL,
                                      `complexity` varchar(255) DEFAULT NULL,
                                      `duration` int(11) NOT NULL,
                                      `number_of_questions` int(11) NOT NULL DEFAULT '0',
                                      PRIMARY KEY (`test_id`));

CREATE TABLE `db_servlet`.`test_questions` (
                                               `question_id` int(11) NOT NULL AUTO_INCREMENT,
                                               `question_text` varchar(255) NOT NULL,
                                               `test_id` int(11) NOT NULL,
                                               PRIMARY KEY (`question_id`));

CREATE TABLE `db_servlet`.`test_options` (
                                             `option_id` int(11) NOT NULL AUTO_INCREMENT,
                                             `option_correct` bit(1) NOT NULL,
                                             `option_text` varchar(255) NOT NULL,
                                             `test_question_id` int(11) NOT NULL,
                                             PRIMARY KEY (`option_id`));

CREATE TABLE `db_servlet`.`student_tests` (
                                              `student_tests_id` int(11) NOT NULL AUTO_INCREMENT,
                                              `actual_end_test_time` datetime(6) DEFAULT NULL,
                                              `end_test` datetime(6) NOT NULL,
                                              `result` float DEFAULT NULL,
                                              `start_test` datetime(6) NOT NULL,
                                              `student_id` int(11) NOT NULL,
                                              `test_id` int(11) DEFAULT NULL,
                                              PRIMARY KEY (`student_tests_id`));

ALTER TABLE `db_servlet`.`usr` ADD FOREIGN KEY (role_id) REFERENCES roles(id);
ALTER TABLE `db_servlet`.`test_questions` ADD FOREIGN KEY (`test_id`) REFERENCES `tests` (`test_id`);
ALTER TABLE `db_servlet`.`test_options` ADD FOREIGN KEY (`test_question_id`) REFERENCES `test_questions` (`question_id`);
ALTER TABLE `db_servlet`.`student_tests` ADD FOREIGN KEY (`test_id`) REFERENCES `tests` (`test_id`);
ALTER TABLE `db_servlet`.`student_tests` ADD FOREIGN KEY (`student_id`) REFERENCES `usr` (`usr_id`);

INSERT INTO `db_servlet`.`roles` (`id`, `role`) VALUES ('0', 'ROLE_USER');
INSERT INTO `db_servlet`.`roles` (`id`, `role`) VALUES ('1', 'ROLE_ADMIN');

