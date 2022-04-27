/*
 Navicat Premium Data Transfer

 Source Server         : machi
 Source Server Type    : MySQL
 Source Server Version : 80019
 Source Host           : localhost:3306
 Source Schema         : shika_book_city

 Target Server Type    : MySQL
 Target Server Version : 80019
 File Encoding         : 65001

 Date: 27/04/2022 16:48:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '活动名称',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '封面',
  `update_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '是否删除 1删除',
  `activity_deadline` datetime NOT NULL COMMENT '活动截止时间',
  `is_die` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '2' COMMENT '0代表进行中1代表已结束2无状态',
  `is_publish` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '1已发布',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  `commodity_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (1, '夏日畅想111', 265.00, '打折销售', '1', '用户本人', '2022-04-24 18:32:12', '2022-04-23 15:55:07', 1, '2022-05-26 23:40:46', '1', '1', '2022-05-24 18:59:59', '15215142');
INSERT INTO `activity` VALUES (2, '秋风爽爽', 652.00, '打折销售', '1', 'system', '2022-04-23 17:44:57', '2022-04-23 17:44:59', 0, '2022-04-23 17:45:02', '1', '1', '2022-04-20 13:19:40', '85125632');
INSERT INTO `activity` VALUES (3, '春意昂立', 888.00, '打折', '1', '用户本人', '2022-04-26 15:28:09', '2022-04-23 19:20:12', 0, '2022-05-28 17:14:11', '0', '1', '2022-05-25 11:23:05', '85625452');
INSERT INTO `activity` VALUES (4, '寒冬将至', 1688.00, '打折', '1', '用户本人', '2022-04-26 15:29:11', '2022-04-24 10:07:55', 0, '2022-05-26 11:05:21', '0', '1', '2022-05-18 19:23:18', '85425232');
INSERT INTO `activity` VALUES (5, 'asd', 123.00, 'asd', 'https://s1.ax1x.com/2022/04/24/L4l2wt.jpg', 'system', '2022-04-24 13:52:05', '2022-04-24 13:52:05', 0, '2022-04-18 00:00:00', '1', '1', '2022-04-16 00:00:00', '91532458');
INSERT INTO `activity` VALUES (6, 'adsdas', 123.00, 'sdasdasd', 'https://s1.ax1x.com/2022/04/24/L488SS.jpg', '用户本人', '2022-04-26 15:28:55', '2022-04-24 14:09:51', 0, '2022-05-23 10:16:25', '2', '1', '2022-04-29 00:23:13', '95125485');
INSERT INTO `activity` VALUES (7, 'adadasd', 588.00, 'qwdqw', 'https://s1.ax1x.com/2022/04/25/LI3qRf.jpg', '用户本人', '2022-04-25 19:36:16', '2022-04-25 09:51:12', 0, '2022-04-30 00:00:00', '2', '0', '2022-04-29 00:00:00', '36251521');
INSERT INTO `activity` VALUES (8, 'qwdqwdqwd', 265.00, 'qwdqwd', 'https://s1.ax1x.com/2022/04/25/LIvJJK.jpg', '用户本人', '2022-04-25 19:33:34', '2022-04-25 14:06:38', 0, '2022-05-23 00:00:00', '2', '0', '2022-04-30 19:26:25', '11587997');
INSERT INTO `activity` VALUES (9, 'asdasd', 255.00, 'asdasddas', 'https://s1.ax1x.com/2022/04/25/LoOXbn.jpg', '用户本人', '2022-04-25 19:26:00', '2022-04-25 19:23:23', 0, '2022-04-25 19:39:48', '2', '1', '2022-04-25 19:39:48', '16227663');

-- ----------------------------
-- Table structure for activity_book
-- ----------------------------
DROP TABLE IF EXISTS `activity_book`;
CREATE TABLE `activity_book`  (
  `activity_id` int NOT NULL COMMENT '活动名称',
  `book_id` int NOT NULL COMMENT '商品信息',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '1为删除',
  PRIMARY KEY (`activity_id`, `book_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity_book
-- ----------------------------
INSERT INTO `activity_book` VALUES (1, 2, '1');
INSERT INTO `activity_book` VALUES (1, 3, '1');
INSERT INTO `activity_book` VALUES (3, 5, '0');
INSERT INTO `activity_book` VALUES (4, 15, '0');
INSERT INTO `activity_book` VALUES (4, 16, '0');
INSERT INTO `activity_book` VALUES (4, 21, '0');
INSERT INTO `activity_book` VALUES (6, 2, '0');
INSERT INTO `activity_book` VALUES (6, 3, '0');
INSERT INTO `activity_book` VALUES (6, 4, '0');
INSERT INTO `activity_book` VALUES (6, 5, '0');
INSERT INTO `activity_book` VALUES (6, 10, '0');
INSERT INTO `activity_book` VALUES (7, 11, '0');
INSERT INTO `activity_book` VALUES (7, 15, '0');
INSERT INTO `activity_book` VALUES (7, 16, '0');
INSERT INTO `activity_book` VALUES (7, 21, '0');
INSERT INTO `activity_book` VALUES (8, 4, '0');
INSERT INTO `activity_book` VALUES (8, 10, '0');
INSERT INTO `activity_book` VALUES (8, 11, '0');
INSERT INTO `activity_book` VALUES (8, 31, '0');
INSERT INTO `activity_book` VALUES (8, 32, '0');
INSERT INTO `activity_book` VALUES (8, 33, '0');
INSERT INTO `activity_book` VALUES (9, 3, '0');
INSERT INTO `activity_book` VALUES (9, 4, '0');
INSERT INTO `activity_book` VALUES (9, 21, '0');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书籍姓名',
  `ISBN` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'ISBN',
  `commodity_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品编码',
  `publication_time` datetime NOT NULL COMMENT '出版时间',
  `brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '品牌',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者',
  `packaging` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '包装类型',
  `the_paper` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用纸类型',
  `format` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '开本类型',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '封面',
  `press` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '出版社',
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '否删除 1 删除',
  `is_added` int NULL DEFAULT 0 COMMENT '是否上架 1上架',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES (2, '夏日终曲(第90届奥斯卡最佳改编剧本奖电影《请以你的名字呼唤我》原著小说) [Call Me by Your Name]', '9787513598251', '72026178', '2018-02-01 00:00:00', '外研', 39.00, '（美）安德烈·艾席蒙', '32开', '胶版纸', '32开', 'https://img14.360buyimg.com/n1/s200x200_jfs/t1/105849/34/19259/297179/5e9d12d7Ebdc8be37/7e07f577bf3480a4.jpg', '外语教学与研究出版社I', '意大利里维埃拉，一个夏天，十七岁的少年埃利奥遇到大他七岁、前来意大利游历的美国大学生奥利弗。 两人彼此着迷、犹疑、试探，渐渐地，情感在流动中迸发。\r\n这场邂逅虽然为时只有六周，却给男孩留下了一生的印记，因为在那个闷热的仲夏，他发现了此生再也无法寻找到的东西 ……', '2022-04-18 17:25:51', '2022-04-24 10:31:04', '用户本人', 0, 1);
INSERT INTO `book` VALUES (3, '果麦经典：小王子（畅销400万册纪念版，李继宏口碑译作，作者基金会官方认证简体中文版）', '9787201077642', '24654732', '2013-01-01 00:00:00', '果麦', 39.80, '[法]安托万·德·圣埃克苏佩里', '32开', '胶版纸', '32开', 'https://img13.360buyimg.com/n1/s200x200_jfs/t1/177401/24/19598/280083/611a1473E19a282d3/bbc3ee2ebaa2122e.jpg', '天津人民出版社', '遥远星球上的小王子，与美丽而骄傲的玫瑰吵架负气出走，在各星球漫游中，小王子遇到了傲慢的国王、酒鬼、惟利是图的商人，死守教条的地理学家，后来来到地球上，试图找到治愈孤独和痛苦的良方。\r\n\r\n这时，他遇到一只奇怪的狐狸，于是奇妙而令人惊叹的事情发生了……\r\n\r\n《小王子》犹如透亮的镜子，照出了荒唐的成人世界。她在提醒我们，只有爱，才是人生的哲学，才是我们活下去的理由。', '2022-04-18 17:34:17', '2022-04-23 14:24:29', '用户本人', 0, 1);
INSERT INTO `book` VALUES (4, '红楼梦原著版（套装上下册）（全两册）（中国古典文学读本丛书，1-9年级必读书单）', '9787020002201', '51257439', '2008-07-01 00:00:00', '人民文学出版社', 37.10, '曹雪芹', '32开', '胶版纸', '32开', 'https://img10.360buyimg.com/n1/s200x200_jfs/t1/63654/33/2304/258890/5d0a144cEbfdd8b73/b6a56c1a8095ab1d.jpg', '人民文学出版社', '《红楼梦》塑造了众多的人物形象，他们各自具有自己独特而鲜明的个性特征，成为不朽的艺术典型，在中国文学史和世界文学史上永远放射着奇光异彩。　　《红楼梦》是一部具有高度思想性和高度艺术性的伟大作品，从《红楼梦》反映的思想倾向来看，作者具有初步的民主主义思想，他对现实社会包括宫廷及官场的黑暗，封建贵族阶级及其家庭的腐朽，封建的科举制度、婚姻制度、奴婢制度、等级制度，以及与此相适应的社会统治思想即孔孟之道和程朱理学、社会道德观念等等，都进行了深刻的批判并且提出了朦胧的带有初步民主主义性质的理想和主张。这些理想和主张正是当时正在滋长的资本主义经济萌芽因素的曲折反映。', '2022-04-18 17:37:57', '2022-04-22 14:04:21', '用户本人', 0, 1);
INSERT INTO `book` VALUES (5, '马尔克斯：百年孤独（50周年纪念版）', '9787544291170', '68980489', '2017-08-01 00:00:00', '新经典', 54.40, '加西亚·马尔克斯', '32开', '书写纸', '32开', 'https://img10.360buyimg.com/n1/s200x200_jfs/t1/136718/36/19411/347041/5fd22887Ee4fd7271/1d14a56986553ced.jpg', '南海出版公司', '《百年孤独》是魔幻现实主义文学的代表作，描写了布恩迪亚家族七代人的传奇故事，以及加勒比海沿岸小镇马孔多的百年兴衰，反映了拉丁美洲一个世纪以来风云变幻的历史。作品融入神话传说、民间故事、宗教典故等因素，巧妙地糅合了现实与虚幻，展现出一个瑰丽的想象世界，成为20世纪重要的经典文学巨著。1982年加西亚·马尔克斯获得诺贝尔文学奖，奠定世界文学大师的地位，很大程度上便是凭借《百年孤独》的巨大影响。', '2022-04-18 17:40:13', '2022-04-23 14:03:25', '用户本人', 0, 1);
INSERT INTO `book` VALUES (9, '小女生职场修行记1', '9787536062139', '58174667', '2020-06-01 00:00:00', '广东花城出版社', 23.90, '水青', '32开', '轻型纸', '32开', 'https://img14.360buyimg.com/n1/s200x200_jfs/t1/194707/29/7536/53580/60c2c5f8Ecfdb98a6/5839d277fe4d529b.jpg', '花城出版社', '一位刚从学校毕业的80后小女生，初入职场，事事不顺，差点被炒鱿鱼，又不小心当了小三。她在最失意时得到一位精通传统智慧的高人指导，从一个小业务员成为公司的高级管理人员，收入增加了十倍，还找到了满意的伴侣。她的男友本是一位诸事不顺的低等公务员，在她和高人的指点下也得到了晋升，开始觉得事事顺利。\r\n\r\n他们经历的事，几乎每天都在你我的周围发生，他们并没有得到特别的机遇，更没有任何关系、背景，他们使用的方法是中国传统智慧的结晶，是任何一个人都是可以去尝试的。他们所获得的成功，也就是可以复制的。', '2022-04-18 17:51:52', '2022-04-23 15:05:55', '用户本人', 0, 1);
INSERT INTO `book` VALUES (10, '生死疲劳新版 不看不知道，莫言真幽默！在极度痛苦时笑出声来，获得内心深处的解脱。', '9787533966102', '34433333', '2022-01-01 00:00:00', '读客', 69.90, '莫言', '32开', '轻型纸', '32开', 'https://img10.360buyimg.com/n1/s200x200_jfs/t1/105186/24/19748/218153/61c56adaEad4c9838/13bcdce6676eedf1.jpg', '浙江文艺出版社', '不看不知道，欲哭又想笑。活着不容易，幽默无价宝。\r\n\r\n——莫言\r\n\r\n五十年间西门闹经历六次转世，\r\n\r\n一世为驴，二世为牛，三世为猪，四世为狗，五世为猴，最终降生为人。\r\n\r\n在这六世里，他目睹蓝脸一家三代经历人生的生死疲劳，\r\n\r\n他们爱就爱到底，恨就恨到底，犟就犟到底，干就干到底，\r\n\r\n有极致的痛苦与欢乐，更有不灭的热情与希望。\r\n\r\n而他们的故事，要从1950年1月1日讲起……', '2022-04-18 17:59:34', '2022-04-22 12:24:15', '用户本人', 0, 1);
INSERT INTO `book` VALUES (11, '人生海海（麦家重磅力作，莫言、董卿盛赞，连续两年高居畅销榜，发行量超200万册）', '9787530219218', '88716180', '2019-04-01 00:00:00', '新经典', 55.00, '麦家', '32开', '书写纸', '32开', 'https://img12.360buyimg.com/n1/s200x200_jfs/t1/107939/5/12366/134717/5e956404E0fc075a9/e7dc74321073b281.jpg', '北京十月文艺出版社', '“他是全村出奇古怪的人，古怪的名目要扳着指头一个一个数：\r\n\r\n一，他当过国民党上校，是革命群众要斗争的对象。但大家一边斗争他，一边又巴结讨好他，家里出什么事都去找他拿主意。\r\n\r\n二，说他是太监，可我们小孩子经常偷看他那个地方，好像还是满当的，有模有样的。\r\n\r\n三，他向来不出工，不干农活，天天空在家里看报纸，嗑瓜子，可日子过得比谁家都舒坦。还像养孩子一样养着一对猫，宝贝得不得了，简直神经病！”\r\n\r\n《人生海海》是茅盾文学奖得主麦家于2019年推出的全新长篇小说，悬念迭出，气度恢弘。故事背景跨越近一个世纪，巧用孩童视角，围绕一个待解谜团，讲述了一个人在时代中穿行缠斗的一生，离奇的故事里藏着让人悠长叹息的人生况味。\r\n\r\n人生似海，装载着时代、传奇与人心，既有日常滋生的残酷，也有时间带来的仁慈。', '2022-04-19 23:07:15', '2022-04-23 15:09:47', '用户本人', 0, 1);
INSERT INTO `book` VALUES (12, '东野圭吾：解忧杂货店（胡歌、王俊凯、刘昊然倾情推荐，简体中文1000万册纪念版）', '9787544298995', '28001354', '2020-09-01 00:00:00', '新经典', 59.00, '东野圭吾', '32开', '书写纸', '32开', 'https://img14.360buyimg.com/n1/s200x200_jfs/t1/141482/15/7240/549362/5f4c5e8eEbf0eb044/fcfa0b7da3acb19a.jpg', '南海出版公司', '《解忧杂货店》是东野圭吾的奇迹之作，运用巧妙的构思和奇幻的设定，让温情、惊喜与感动悄然渗入读者心中，回味无穷。《解忧杂货店》的销量已突破1000万册，为此，东野圭吾特别寄语中国读者：“《解忧杂货店》能在中国拥有这么多读者，对我而言既是荣幸也是鼓励。我想在这本书中写的，是无论身处哪个国家、哪个时代的人都一定会抱有的‘梦想’与‘烦恼’。人之所以为人，正是因为这无尽的梦想与烦恼吧。”\r\n\r\n内容简介：\r\n\r\n僻静的街道上有一家杂货店，只要写下烦恼投进卷帘门的投信口，第二天就会在店后的牛奶箱里得到回答。\r\n\r\n因男友身患绝症，在爱情与梦想间徘徊；为了音乐梦想离家漂泊，却在现实中寸步难行；面临家庭巨变，挣扎在亲情与未来的迷茫中……\r\n\r\n他们将困惑写成信投进杂货店，随即奇妙的事情竟不断发生。', '2022-04-19 23:09:52', '2022-04-25 09:43:08', '用户本人', 0, 1);
INSERT INTO `book` VALUES (13, '东野圭吾：秘密（日本读者票选东野作品第 2名，仅次于《白夜行》', '9787544258210', '16754935', '2022-04-12 00:00:00', '新经典', 40.50, '东野圭吾', '32开', '轻型纸', '32开', 'https://img10.360buyimg.com/n1/s200x200_jfs/t8023/249/2495169622/358200/8def2188/59cdf6deN491ebd9d.jpg', '南海出版公司', '《秘密》是东野圭吾的长篇小说代表作，创作生涯里程碑作品，在众多读者心目中仅次于《白夜行》。东野曾说：“《秘密》是上天对我兢兢业业努力写作的褒奖。”如果用一句话来总结《秘密》讲述的故事，那就是：这是一个只有两个人的三口之家，他们必须保守这个秘密。\r\n\r\n《秘密》不是推理小说，却获得日本推理作家协会奖，并领衔《书的杂志》年度推理小说排行榜，日本销量突破200万册。从《秘密》开始，东野圭吾的作品被翻译成英文走向西方世界。可以说，《秘密》在奖项、销量和题材上得到了东方、西方读者的普遍欢迎。\r\n\r\n平介的幸福生活在39岁那年被摧毁了。妻子带着女儿回娘家探亲，乘坐的大巴意外坠落山崖。妻子拼命护住女儿，最终伤重不治；女儿毫发未伤，但一直昏迷。妻子离世的那一刻，女儿奇迹般醒了过来。\r\n\r\n平介强忍悲痛为妻子办完葬礼，回到病房，看到女儿在熟睡，他忍不住呜咽着呼喊妻子。这时突然传来一个声音：“……公。”平介吓了一跳，却四顾无人，正疑惑时又听到了那声音：“老公，我……我在这儿。”', '2022-04-19 23:11:45', '2022-04-21 13:52:03', '用户本人', 0, 1);
INSERT INTO `book` VALUES (15, '绝叫（罗翔推荐，豆瓣 2020年度图书 推理·悬疑 Top 1，豆瓣 2019年度电影榜单 Top10《绝叫》原著）', '9787559641182', '12696381', '2020-07-01 00:00:00', '联合读创', 58.00, '叶真中显', '平装', '纯质纸', '32开', 'https://img11.360buyimg.com/n1/s200x200_jfs/t1/113898/29/12925/325059/5f17db6dEbdad9e47/c82f2900845461d5.jpg', '北京联合出版公司', '铃木阳子死了，死在独居的公寓里。\r\n\r\n正确说来，是铃木阳子几个月前死了。因为发现她时，遗体不但遭到屋内的十一只猫啃食殆尽，连猫也全数饿死了。\r\n\r\n铃木阳子显然是“孤独死”的最佳范例，但这名女子为何落到这步田地？她的亲人朋友同事在哪里？她的人生轨迹又是如何？', '2022-04-19 23:16:07', '2022-04-19 23:16:12', 'system', 0, 1);
INSERT INTO `book` VALUES (16, '望江南（你一句春不晚，我就到了真江南。茅奖得主王旭烽，沉潜26年，最新长篇小说力作。）', '9787533963354', '13613372', '2022-03-01 00:00:00', '浙江文艺出版社', 68.00, '王旭烽', '平装', '胶版纸', '32开', 'https://img12.360buyimg.com/n1/s200x200_jfs/t1/95241/5/23668/131900/621dd7fdE5ea426ce/071d68b738a879df.jpg', '浙江文艺出版社', '《望江南》是茅盾文学奖得主王旭烽沉潜26年后创作的又一长篇小说。小说叙述了新中国成立前后近二十年间波澜壮阔的社会进程中，江南茶叶世家杭氏家族的起落浮沉和人物命运。\r\n\r\n王旭烽写茶人茶事，在翻天覆地的大时代中，写出了江南烟雨浸润中的中国人的选择和敞开、融入和奋进；在时代的激昂与风雷声中，王旭烽写得悠远低回又荡气回肠，写出了茶香和茶性，写出了江南文化的诗意和力量，写出了中国的风度、情怀和品格。\r\n\r\n杭家人的故事也是中国的故事，它交集了历史回忆和情感想象，既是对消逝的时间的重构，也是对文化传统现代传承的探索。这部小说为中国生活和精神的剧变与恒常提供了一种新的叙事，见证了个人史、家族史、民族史中的百年中国。', '2022-04-19 23:18:47', '2022-04-19 23:18:52', 'system', 0, 1);
INSERT INTO `book` VALUES (20, '儒林外史（精装典藏版）', '9787519020774', '12365828', '2016-10-01 00:00:00', '翰墨怡香', 22.80, '吴敬梓', '精装', '胶版纸', '32开', 'https://img13.360buyimg.com/n1/s200x200_jfs/t1/116490/25/6849/253377/5ebe5495E19b38d7b/f6d38e41194f963c.jpg', '中国文联出版社', '《儒林外史》作为我国古代第一部现实主义长篇讽刺小说，主要描写封建知识分子的生活和精神状态，揭露科举制度的腐朽以及在这个制度奴役下的士人的丑恶灵魂。它继承了先秦诸子讽刺寓言的成果，对明代或同时代的粗浅长篇讽刺小说《西游记》《西游补》等，《聊斋志异》中的短篇讽刺小说等的主题思想和艺术手法进行了继承，并大胆突破，成为真正的讽刺文学，“于是说部中乃始有足称讽刺之书”（鲁迅《中国小说史略》），对后世产生了深远影响。', '2022-04-19 23:27:25', '2022-04-19 23:27:29', 'system', 0, 1);
INSERT INTO `book` VALUES (21, '东野圭吾：虚无的十字架（罗翔推荐！上市首周登顶Oricon排行榜！《白夜行》式危险关系，《恶意》式强烈反转）', '9787544263962', '13095511', '2022-02-01 00:00:00', '新经典', 53.10, '东野圭吾', '精装', '书写纸', '32开', 'https://img11.360buyimg.com/n1/s200x200_jfs/t1/124609/3/20635/352755/6259a111Ef7b9a010/1fb15bca45d2580e.jpg', '南海出版公司', '《虚无的十字架》充满了东野圭吾对法理与人性的深切思考，将反转离奇的推理过程与思辨之力相结合，每一声拷问都钝重清晰、振聋发聩，却又如鲠在喉。\r\n\r\n“回想那一天，我输入了约定好的传呼信息。\r\n\r\n不知过了多久，我在恍惚间听见了门把手转动的声音。\r\n\r\n我像蛇一样在地面爬行，耗尽了浑身的力气才抵达玄关。\r\n\r\n站在门口的是我蕞爱的人，也是我的同谋。我们一起掩埋了罪证。\r\n\r\n我想，做出了这么可怕的事，我这辈子都不会得到幸福了。那么，就用以后的人生来偿还吧。\r\n\r\n\r\n\r\n二十一年过去了。蕞近，我却觉得有谁在暗处盯着我。”', '2022-04-19 23:29:28', '2022-04-19 23:29:30', 'system', 0, 1);
INSERT INTO `book` VALUES (24, '恰同学少年（写透青年伟人背后的故事，追寻伟人思想的形成过程，畅销14年，累计销量突破100万册，豆瓣8.6分高口碑推荐！）', '9787543849020', '12654683', '2020-06-01 00:00:00', '湖南人民出版社', 25.20, '黄晖 ', '平装', '胶版纸', '16开', 'https://img13.360buyimg.com/n1/s200x200_jfs/t1/192130/26/18018/125462/6119bcffEe1b2fcbf/407b23998ff68f22.jpg', '湖南人民出版社', '本书以毛泽东等青年在湖南第一师范五年半（1913-1918）的求学生活为主线，充分展现了以毛泽东、蔡和森、向警予、杨开慧、陶斯咏等为代表的优秀青年为寻求理想而奋发向上的斗志，敢为天下为己任的抱负与情怀，改造中国与世界的雄心壮志。同时塑造了杨昌济、孔昭绶等一批很好教师形象，深刻揭示了“学生应该怎样读书，教师应该怎样育人”这个与当今社会紧密相关的现实主题。', '2022-04-19 23:33:36', '2022-04-19 23:33:38', 'system', 0, 0);
INSERT INTO `book` VALUES (25, '恰同学少年（写透青年伟人背后的故事，追寻伟人思想的形成过程，畅销14年，累计销量突破100万册，豆瓣8.6分高口碑推荐！）', '9787543849020', '12654683', '2020-06-01 00:00:00', '湖南人民出版社', 25.20, '黄晖 ', '平装', '胶版纸', '16开', 'https://img13.360buyimg.com/n1/s200x200_jfs/t1/192130/26/18018/125462/6119bcffEe1b2fcbf/407b23998ff68f22.jpg', '湖南人民出版社', '本书以毛泽东等青年在湖南第一师范五年半（1913-1918）的求学生活为主线，充分展现了以毛泽东、蔡和森、向警予、杨开慧、陶斯咏等为代表的优秀青年为寻求理想而奋发向上的斗志，敢为天下为己任的抱负与情怀，改造中国与世界的雄心壮志。同时塑造了杨昌济、孔昭绶等一批很好教师形象，深刻揭示了“学生应该怎样读书，教师应该怎样育人”这个与当今社会紧密相关的现实主题。', '2022-04-19 23:33:36', '2022-04-19 23:33:38', 'system', 0, 0);
INSERT INTO `book` VALUES (26, '恰同学少年（写透青年伟人背后的故事，追寻伟人思想的形成过程，畅销14年，累计销量突破100万册，豆瓣8.6分高口碑推荐！）', '9787543849020', '12654683', '2020-06-01 00:00:00', '湖南人民出版社', 25.20, '黄晖 ', '平装', '胶版纸', '16开', 'https://img13.360buyimg.com/n1/s200x200_jfs/t1/192130/26/18018/125462/6119bcffEe1b2fcbf/407b23998ff68f22.jpg', '湖南人民出版社', '本书以毛泽东等青年在湖南第一师范五年半（1913-1918）的求学生活为主线，充分展现了以毛泽东、蔡和森、向警予、杨开慧、陶斯咏等为代表的优秀青年为寻求理想而奋发向上的斗志，敢为天下为己任的抱负与情怀，改造中国与世界的雄心壮志。同时塑造了杨昌济、孔昭绶等一批很好教师形象，深刻揭示了“学生应该怎样读书，教师应该怎样育人”这个与当今社会紧密相关的现实主题。', '2022-04-19 23:33:36', '2022-04-19 23:33:38', 'system', 0, 0);
INSERT INTO `book` VALUES (29, 'aaa', '1512121214121', '32495667', '2022-04-20 00:00:00', 'aa', 1.00, 'sas', '骑马订装', '特种纸', '16开', 'https://s1.ax1x.com/2022/04/21/L6M1SK.jpg', 'asa', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', '2022-04-21 13:57:26', '2022-04-21 13:57:26', '用户本人', 0, 0);
INSERT INTO `book` VALUES (30, 'aaaaaaaaaaaaaa', '1212121512121', '22763072', '2022-04-11 00:00:00', '1', 1.00, '11', '平装', '灰纸板', '16开', 'https://s1.ax1x.com/2022/04/21/L6NoPe.jpg', 'aaa', 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa', '2022-04-21 15:00:16', '2022-04-21 15:00:16', '用户本人', 0, 0);
INSERT INTO `book` VALUES (31, 'a', '1111111111111', '95399674', '2022-04-11 00:00:00', 'aa', 11.00, 'aa', '32开', '特种纸', '32开', 'https://s1.ax1x.com/2022/04/25/LoHyO1.jpg', 'a', 'aaaaaaaaaaaaaaaaaa', '2022-04-21 15:07:36', '2022-04-25 18:39:56', '用户本人', 0, 1);
INSERT INTO `book` VALUES (32, 'aaaaaaaaaaaa', '1521251254121', '70342122', '2022-04-19 00:00:00', 'asasasa', 55.00, 'aasad', '16开', '胶版纸', '16开', 'https://s1.ax1x.com/2022/04/21/L62bbF.jpg', 'asdad', 'asdasdadasdasdasd', '2022-04-21 15:17:51', '2022-04-23 15:06:43', '用户本人', 0, 1);
INSERT INTO `book` VALUES (33, 'aaaaaaaaaaaaaa', '1521252121412', '8704807', '2022-04-12 00:00:00', 'asas', 255.00, 'asasa', '32开', '轻型纸', '32开', 'https://s1.ax1x.com/2022/04/22/LgVII0.jpg', 'sas', 'asasas', '2022-04-22 00:01:57', '2022-04-23 15:06:27', '用户本人', 0, 1);
INSERT INTO `book` VALUES (34, 'asdasd', '1521212512125', '64377076', '2022-04-13 00:00:00', 'asdas', 252.00, 'dasd', '16开', '特种纸', '16开', 'https://s1.ax1x.com/2022/04/22/Lgzc4J.jpg', 'asdasd', 'asdasda', '2022-04-22 12:33:50', '2022-04-22 13:55:55', '用户本人', 0, 1);
INSERT INTO `book` VALUES (35, '夏日', '1251212121212', '44221366', '2022-04-19 00:00:00', 'asda', 152.00, 'dasd', '32开', '特种纸', '32开', 'https://s1.ax1x.com/2022/04/23/LWhbbn.jpg', 'asdasd', 'asdasdasd', '2022-04-23 14:18:18', '2022-04-25 09:42:59', '用户本人', 0, 0);
INSERT INTO `book` VALUES (36, 'asdasd', '12121214151', '36866798', '2022-04-11 00:00:00', 'dqwd', 12.00, 'asd', '16开', '特种纸', '16开', 'https://s1.ax1x.com/2022/04/23/LW47RO.jpg', 'aas', 'asas', '2022-04-23 14:19:39', '2022-04-23 14:25:30', '用户本人', 0, 0);
INSERT INTO `book` VALUES (37, 'adc', '1255212121221', '28213162', '2022-04-15 00:00:00', 'asd', 134.00, 'asa', '32开', '超感纸', '32开', 'https://s1.ax1x.com/2022/04/23/LW5Ees.jpg', 'sasas', 'asas', '2022-04-23 14:21:27', '2022-04-23 14:23:31', '用户本人', 0, 0);
INSERT INTO `book` VALUES (38, 'woc', '1251212121212', '33764040', '2022-04-18 00:00:00', 'qwd', 125.00, 'dqwd', '平装', '胶版纸', '32', 'https://s1.ax1x.com/2022/04/23/LWIdNq.jpg', 'qwdwqd', 'asdasdasdasdasd', '2022-04-23 14:36:37', '2022-04-23 14:36:37', '用户本人', 0, 0);
INSERT INTO `book` VALUES (39, 'qwdqdw', '1251252121212', '9951261', '2022-04-12 00:00:00', 'dasd', 2323.00, 'asd', '线装', '纯质纸', '32', 'https://s1.ax1x.com/2022/04/23/LWocz8.jpg', 'asdasd', 'asdasdad', '2022-04-23 14:42:14', '2022-04-23 14:42:14', '用户本人', 0, 0);
INSERT INTO `book` VALUES (40, 'wdqdwqd', '1521212521212', '8676653', '2022-04-13 00:00:00', 'asda', 265.00, 'sa', '平装', '胶版纸', '32', 'https://s1.ax1x.com/2022/04/25/LIOka6.jpg', 'dasdad', 'asdasdads', '2022-04-25 13:42:31', '2022-04-25 13:42:31', '用户本人', 0, 0);
INSERT INTO `book` VALUES (41, 'asdasdsa', '1231121323212', '32111440', '2022-04-11 00:00:00', 'adasda', 1213.00, 'asdas', '线装', '特种纸', '16', 'https://s1.ax1x.com/2022/04/25/LIjCAx.jpg', 'dasd', 'asdasd', '2022-04-25 13:53:28', '2022-04-25 13:53:28', '用户本人', 0, 0);
INSERT INTO `book` VALUES (42, 'wefwefe', '1252121212141', '96553288', '2022-04-18 00:00:00', 'asda', 255.00, 'dasd', '平装', '胶版纸', '16', 'https://s1.ax1x.com/2022/04/25/LoqLRg.jpg', 'asdasd', 'asdasdad', '2022-04-25 18:57:23', '2022-04-25 18:57:23', '用户本人', 0, 0);

-- ----------------------------
-- Table structure for book_comment
-- ----------------------------
DROP TABLE IF EXISTS `book_comment`;
CREATE TABLE `book_comment`  (
  `commodity_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品编号',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户',
  `comment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评论',
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户昵称',
  `comment_time` datetime NOT NULL COMMENT '评论时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_comment
-- ----------------------------
INSERT INTO `book_comment` VALUES ('72026178', 'shkalu', '好', 'https://s1.ax1x.com/2022/04/18/LdFALd.jpg', '你似璀璨星光', '2022-04-26 14:59:08');

-- ----------------------------
-- Table structure for book_type
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type`  (
  `book_id` int NOT NULL COMMENT '书籍id',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型',
  PRIMARY KEY (`book_id`, `type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES (2, '军事');
INSERT INTO `book_type` VALUES (2, '社会');
INSERT INTO `book_type` VALUES (2, '言情');
INSERT INTO `book_type` VALUES (3, '军事');
INSERT INTO `book_type` VALUES (3, '社会');
INSERT INTO `book_type` VALUES (3, '穿越/重生');
INSERT INTO `book_type` VALUES (4, '军事');
INSERT INTO `book_type` VALUES (4, '外国小说');
INSERT INTO `book_type` VALUES (4, '穿越/重生');
INSERT INTO `book_type` VALUES (5, '军事');
INSERT INTO `book_type` VALUES (5, '社会');
INSERT INTO `book_type` VALUES (5, '穿越/重生');
INSERT INTO `book_type` VALUES (8, '侦探/悬疑/推理');
INSERT INTO `book_type` VALUES (8, '社会');
INSERT INTO `book_type` VALUES (8, '言情');
INSERT INTO `book_type` VALUES (9, '言情');
INSERT INTO `book_type` VALUES (10, '穿越');
INSERT INTO `book_type` VALUES (11, '军事');
INSERT INTO `book_type` VALUES (11, '社会');
INSERT INTO `book_type` VALUES (11, '言情');
INSERT INTO `book_type` VALUES (12, '中国当代小说');
INSERT INTO `book_type` VALUES (12, '惊悚/恐怖');
INSERT INTO `book_type` VALUES (12, '穿越/重生');
INSERT INTO `book_type` VALUES (32, '军事');
INSERT INTO `book_type` VALUES (33, '穿越/重生');
INSERT INTO `book_type` VALUES (33, '言情');
INSERT INTO `book_type` VALUES (34, '武侠');
INSERT INTO `book_type` VALUES (34, '社会');
INSERT INTO `book_type` VALUES (34, '言情');
INSERT INTO `book_type` VALUES (35, '军事');
INSERT INTO `book_type` VALUES (35, '社会');
INSERT INTO `book_type` VALUES (35, '穿越/重生');
INSERT INTO `book_type` VALUES (36, '军事');
INSERT INTO `book_type` VALUES (36, '社会');
INSERT INTO `book_type` VALUES (36, '言情');
INSERT INTO `book_type` VALUES (37, '中国古典小说');
INSERT INTO `book_type` VALUES (37, '历史');
INSERT INTO `book_type` VALUES (37, '玄幻/奇幻');
INSERT INTO `book_type` VALUES (39, '军事');
INSERT INTO `book_type` VALUES (39, '社会');
INSERT INTO `book_type` VALUES (39, '穿越/重生');
INSERT INTO `book_type` VALUES (40, '军事');
INSERT INTO `book_type` VALUES (40, '外国小说');
INSERT INTO `book_type` VALUES (40, '穿越/重生');
INSERT INTO `book_type` VALUES (41, '军事');
INSERT INTO `book_type` VALUES (41, '外国小说');
INSERT INTO `book_type` VALUES (41, '武侠');
INSERT INTO `book_type` VALUES (42, '中国当代小说');
INSERT INTO `book_type` VALUES (42, '外国小说');
INSERT INTO `book_type` VALUES (42, '武侠');

-- ----------------------------
-- Table structure for expressage
-- ----------------------------
DROP TABLE IF EXISTS `expressage`;
CREATE TABLE `expressage`  (
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单号',
  `position` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '当前位置',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否删除 1为删除',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话号码',
  `is_signed` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '1为已签收',
  `commodity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of expressage
-- ----------------------------
INSERT INTO `expressage` VALUES ('2022042512521252', '江西科技学院', '0', '你似那璀璨星光', '13155884431', '0', '111');
INSERT INTO `expressage` VALUES ('2022042521252121', '打包中', '0', '你似那璀璨星光', '13155884431', '1', '绝叫（罗翔推荐，豆瓣 2020年度图书 推理·悬疑 Top 1，豆瓣 2019年度电影榜单 Top10《绝叫》原著）x1; 东野圭吾：秘密（日本读者票选东野作品第 2名，仅次于《白夜行》x2; ');

-- ----------------------------
-- Table structure for marketing_amount
-- ----------------------------
DROP TABLE IF EXISTS `marketing_amount`;
CREATE TABLE `marketing_amount`  (
  `data` date NOT NULL COMMENT '日期',
  `marketing_amount` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '营销额',
  `commodity_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品编码',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否删除'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of marketing_amount
-- ----------------------------
INSERT INTO `marketing_amount` VALUES ('2022-04-26', '5000', '51257439', '红楼梦原著版（套装上下册）（全两册）（中国古典文学读本丛书，1-9年级必读书单）', '0');
INSERT INTO `marketing_amount` VALUES ('2022-04-25', '15121', '36251521', 'qwdqw', '0');
INSERT INTO `marketing_amount` VALUES ('2022-04-24', '1212', '72026178', '夏日终曲(第90届奥斯卡最佳改编剧本奖电影《请以你的名字呼唤我》原著小说) [Call Me by Your Name]', '0');
INSERT INTO `marketing_amount` VALUES ('2022-03-23', '1212', '28001354', '东野圭吾：解忧杂货店（胡歌、王俊凯、刘昊然倾情推荐，简体中文1000万册纪念版）', '0');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单号',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货地址',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人',
  `is_send` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否发货 0 未发货',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人手机号码',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否删除 1删除',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('2022042512521252', '江西省抚州市南城县', '你似那璀璨星光', '0', '2022-04-25 15:14:38', '2022-04-25 15:14:40', 'system', '13155884431', '0');
INSERT INTO `order` VALUES ('2022042521252121', '江西省南昌市南昌县江西科技学院', '你似那璀璨星光', '1', '2022-04-25 15:17:34', '2022-04-25 15:17:37', 'system', '13155884431', '0');

-- ----------------------------
-- Table structure for order_commodity
-- ----------------------------
DROP TABLE IF EXISTS `order_commodity`;
CREATE TABLE `order_commodity`  (
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单id',
  `commodity_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '书籍id',
  `quantities` int NOT NULL COMMENT '数量',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否删除 1为删除'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order_commodity
-- ----------------------------
INSERT INTO `order_commodity` VALUES ('2022042512521252', '11587997', 2, '0');
INSERT INTO `order_commodity` VALUES ('2022042512521252', '72026178', 1, '0');
INSERT INTO `order_commodity` VALUES ('2022042521252121', '12696381', 1, '0');
INSERT INTO `order_commodity` VALUES ('2022042521252121', '16754935', 2, '0');

-- ----------------------------
-- Table structure for publish
-- ----------------------------
DROP TABLE IF EXISTS `publish`;
CREATE TABLE `publish`  (
  `activity_id` int NOT NULL COMMENT '活动id',
  `activity_deadline` datetime NOT NULL COMMENT '活动截止时间',
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '是否删除 1为 删除',
  `start_time` datetime NOT NULL COMMENT '活动开始时间',
  PRIMARY KEY (`activity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of publish
-- ----------------------------
INSERT INTO `publish` VALUES (1, '2022-04-23 00:00:00', '1', '2022-04-20 10:10:45');
INSERT INTO `publish` VALUES (2, '2022-04-28 00:00:00', '0', '2022-04-29 00:00:00');
INSERT INTO `publish` VALUES (3, '2022-04-29 18:41:12', '2', '2022-04-13 18:41:15');
INSERT INTO `publish` VALUES (4, '2022-04-30 18:41:22', '2', '2022-04-12 18:41:25');
INSERT INTO `publish` VALUES (5, '2022-04-18 00:00:00', '1', '2022-04-16 00:00:00');

-- ----------------------------
-- Table structure for sales
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales`  (
  `book_id` int NOT NULL COMMENT '书籍id',
  `sales` int NOT NULL COMMENT '销量',
  `date` date NOT NULL COMMENT '日期',
  `del_flag` int NULL DEFAULT 0 COMMENT '是否删除 1删除'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sales
-- ----------------------------
INSERT INTO `sales` VALUES (2, 50, '2022-04-26', 0);
INSERT INTO `sales` VALUES (3, 100, '2022-04-26', 0);
INSERT INTO `sales` VALUES (4, 60, '2022-04-26', 0);
INSERT INTO `sales` VALUES (15, 500, '2022-04-26', 0);
INSERT INTO `sales` VALUES (14, 200, '2022-03-23', 0);
INSERT INTO `sales` VALUES (12, 500, '2022-03-31', 0);
INSERT INTO `sales` VALUES (2, 1000, '2022-03-24', 0);
INSERT INTO `sales` VALUES (2, 300, '2022-04-27', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '用户名',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号码',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别 1 男 0 女',
  `status` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '是否封禁 1是',
  `login_ip` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '登录地址',
  `login_date` datetime NOT NULL COMMENT '最后登录时间',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `del_flag` int NOT NULL DEFAULT 0 COMMENT '是否删除',
  `update_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '修改人',
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `ipaddr` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '登录地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '241760', '用户_95840', NULL, NULL, '0', ' 本机地址', '2022-04-27 12:20:24', '2022-04-15 16:29:52', '2022-04-15 16:29:52', 0, 'system', 'https://s1.ax1x.com/2022/04/17/LNdyM4.png', '95affa15a4035aa5a3db49ad0ea1c8fd', '127.0.0.1');
INSERT INTO `user` VALUES (2, 'machi', '用户_95840', NULL, NULL, '0', ' 本机地址', '2022-04-16 00:16:01', '2022-04-15 21:50:25', '2022-04-15 21:50:25', 0, 'system', 'https://s1.ax1x.com/2022/04/17/LNdyM4.png', '95affa15a4035aa5a3db49ad0ea1c8fd', '127.0.0.1');
INSERT INTO `user` VALUES (3, 'machi23', '用户_95840', NULL, NULL, '0', ' 本机地址', '2022-04-15 21:50:44', '2022-04-15 21:50:44', '2022-04-15 21:50:44', 0, 'system', 'https://s1.ax1x.com/2022/04/17/LNdyM4.png', '95affa15a4035aa5a3db49ad0ea1c8fd', '127.0.0.1');
INSERT INTO `user` VALUES (4, 'asxasxa', '用户_95840', NULL, NULL, '0', ' 本机地址', '2022-04-15 21:52:55', '2022-04-15 21:52:55', '2022-04-15 21:52:55', 0, 'system', 'https://s1.ax1x.com/2022/04/17/LNdyM4.png', 'e10adc3949ba59abbe56e057f20f883e', '127.0.0.1');
INSERT INTO `user` VALUES (5, 'nange', '用户_95840', NULL, NULL, '0', ' 本机地址', '2022-04-15 21:57:08', '2022-04-15 21:57:08', '2022-04-15 21:57:08', 0, 'system', 'https://s1.ax1x.com/2022/04/17/LNdyM4.png', '95affa15a4035aa5a3db49ad0ea1c8fd', '127.0.0.1');
INSERT INTO `user` VALUES (6, '2417600913', '用户_95840', NULL, NULL, '0', ' 本机地址', '2022-04-16 16:58:59', '2022-04-16 16:54:28', '2022-04-16 16:54:28', 0, 'system', 'https://s1.ax1x.com/2022/04/17/LNdyM4.png', '95affa15a4035aa5a3db49ad0ea1c8fd', '127.0.0.1');
INSERT INTO `user` VALUES (7, '2417600', '用户_64377', NULL, NULL, '0', ' 本机地址', '2022-04-16 19:18:20', '2022-04-16 19:18:20', '2022-04-16 19:18:20', 0, 'system', 'https://s1.ax1x.com/2022/04/17/LNdyM4.png', '95affa15a4035aa5a3db49ad0ea1c8fd', '127.0.0.1');
INSERT INTO `user` VALUES (8, 'shikalu', '你似璀璨星光', '18070444649', '1', '0', ' 本机地址', '2022-04-27 16:46:45', '2022-04-16 19:18:58', '2022-04-27 13:47:23', 0, 'system', 'https://s1.ax1x.com/2022/04/18/LdFALd.jpg', '95affa15a4035aa5a3db49ad0ea1c8fd', '127.0.0.1');
INSERT INTO `user` VALUES (9, 'adcggg', '用户_27518', NULL, NULL, '0', ' 本机地址', '2022-04-17 16:34:42', '2022-04-17 16:34:35', '2022-04-17 16:34:35', 0, 'system', 'https://s1.ax1x.com/2022/04/17/LNdyM4.png', '95affa15a4035aa5a3db49ad0ea1c8fd', '127.0.0.1');
INSERT INTO `user` VALUES (10, '13155884431', 'ADC', '13155884431', '1', '0', ' 本机地址', '2022-04-27 12:19:20', '2022-04-18 13:47:38', '2022-04-21 12:24:22', 0, 'system', 'https://s1.ax1x.com/2022/04/17/LNdyM4.png', '95affa15a4035aa5a3db49ad0ea1c8fd', '127.0.0.1');
INSERT INTO `user` VALUES (11, 'aaaaa', '用户_99440', NULL, NULL, '0', ' 本机地址', '2022-04-20 18:26:58', '2022-04-20 18:26:54', '2022-04-20 18:26:54', 0, 'system', 'https://s1.ax1x.com/2022/04/17/LNdyM4.png', '95affa15a4035aa5a3db49ad0ea1c8fd', '127.0.0.1');

-- ----------------------------
-- Table structure for user_activity
-- ----------------------------
DROP TABLE IF EXISTS `user_activity`;
CREATE TABLE `user_activity`  (
  `user_id` int NOT NULL,
  `activity_id` int NOT NULL,
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '1为删除',
  PRIMARY KEY (`user_id`, `activity_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_activity
-- ----------------------------
INSERT INTO `user_activity` VALUES (8, 1, '1');
INSERT INTO `user_activity` VALUES (8, 2, '0');
INSERT INTO `user_activity` VALUES (8, 3, '0');
INSERT INTO `user_activity` VALUES (8, 4, '0');
INSERT INTO `user_activity` VALUES (8, 5, '0');
INSERT INTO `user_activity` VALUES (8, 6, '0');
INSERT INTO `user_activity` VALUES (8, 7, '0');
INSERT INTO `user_activity` VALUES (8, 8, '0');
INSERT INTO `user_activity` VALUES (8, 9, '0');

-- ----------------------------
-- Table structure for user_book
-- ----------------------------
DROP TABLE IF EXISTS `user_book`;
CREATE TABLE `user_book`  (
  `user_id` int NOT NULL,
  `book_id` int NOT NULL,
  `del_flag` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '1为删除',
  PRIMARY KEY (`user_id`, `book_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_book
-- ----------------------------
INSERT INTO `user_book` VALUES (8, 2, '0');
INSERT INTO `user_book` VALUES (8, 3, '0');
INSERT INTO `user_book` VALUES (8, 4, '0');
INSERT INTO `user_book` VALUES (8, 5, '0');
INSERT INTO `user_book` VALUES (8, 7, '0');
INSERT INTO `user_book` VALUES (8, 9, '0');
INSERT INTO `user_book` VALUES (8, 10, '0');
INSERT INTO `user_book` VALUES (8, 11, '0');
INSERT INTO `user_book` VALUES (8, 12, '0');
INSERT INTO `user_book` VALUES (8, 13, '0');
INSERT INTO `user_book` VALUES (8, 15, '0');
INSERT INTO `user_book` VALUES (8, 16, '0');
INSERT INTO `user_book` VALUES (8, 20, '0');
INSERT INTO `user_book` VALUES (8, 21, '0');
INSERT INTO `user_book` VALUES (8, 31, '0');
INSERT INTO `user_book` VALUES (8, 32, '0');
INSERT INTO `user_book` VALUES (8, 33, '0');
INSERT INTO `user_book` VALUES (8, 34, '1');
INSERT INTO `user_book` VALUES (8, 35, '0');
INSERT INTO `user_book` VALUES (8, 36, '0');
INSERT INTO `user_book` VALUES (8, 37, '1');
INSERT INTO `user_book` VALUES (8, 38, '1');
INSERT INTO `user_book` VALUES (8, 39, '1');
INSERT INTO `user_book` VALUES (8, 40, '0');
INSERT INTO `user_book` VALUES (8, 41, '0');
INSERT INTO `user_book` VALUES (8, 42, '0');
INSERT INTO `user_book` VALUES (10, 2, '0');
INSERT INTO `user_book` VALUES (10, 13, '0');

-- ----------------------------
-- Table structure for user_order
-- ----------------------------
DROP TABLE IF EXISTS `user_order`;
CREATE TABLE `user_order`  (
  `user_id` int NOT NULL,
  `order_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_order
-- ----------------------------
INSERT INTO `user_order` VALUES (8, '2022042512521252');
INSERT INTO `user_order` VALUES (8, '2022042521252121');

SET FOREIGN_KEY_CHECKS = 1;
