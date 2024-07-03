ALTER TABLE IF EXISTS user_roles
ADD CONSTRAINT FK55itppkw3i07do3h7qoclqd4k
FOREIGN KEY (user_id) REFERENCES `user`(user_id);


INSERT INTO `user` VALUES (1,'john','$2a$10$/.KKJU.G71/Fl.4wKo.lJOfDhxhHPo0o.DPxIy98IuKSaK74WXUy2');
INSERT INTO `user` VALUES (2,'anna','$2a$10$/.KKJU.G71/Fl.4wKo.lJOfDhxhHPo0o.DPxIy98IuKSaK74WXUy2');

INSERT INTO `user_roles` VALUES (1, 'toList');
INSERT INTO `user_roles` VALUES (2, 'toList');
INSERT INTO `user_roles` VALUES (2, 'admin');