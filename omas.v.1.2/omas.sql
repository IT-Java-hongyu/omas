/*
SQLyog Ultimate v12.3.1 (64 bit)
MySQL - 5.5.60 : Database - omas
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`omas` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `omas`;

/*Table structure for table `apartment` */

DROP TABLE IF EXISTS `apartment`;

CREATE TABLE `apartment` (
  `apart_id` varchar(32) NOT NULL,
  `apart_name` varchar(25) NOT NULL,
  PRIMARY KEY (`apart_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='科室表';

/*Data for the table `apartment` */

insert  into `apartment`(`apart_id`,`apart_name`) values 
('1','内科'),
('10','心理咨询室'),
('11','呼吸内科'),
('12','消化内科'),
('13','泌尿内科'),
('14','心内科'),
('15','血液科'),
('2','外科'),
('3','儿科'),
('4','妇科'),
('5','眼科'),
('6','耳鼻喉科'),
('7','口腔科'),
('8','皮肤科'),
('9','中医科');

/*Table structure for table `doctor` */

DROP TABLE IF EXISTS `doctor`;

CREATE TABLE `doctor` (
  `doc_id` varchar(32) NOT NULL,
  `doc_name` varchar(25) NOT NULL,
  `sex` int(5) NOT NULL,
  `phone` varchar(25) NOT NULL,
  `synopsis` varchar(255) NOT NULL,
  `tile_id` varchar(32) NOT NULL,
  `apart_id` varchar(32) NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`doc_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生表';

/*Data for the table `doctor` */

insert  into `doctor`(`doc_id`,`doc_name`,`sex`,`phone`,`synopsis`,`tile_id`,`apart_id`,`birthday`) values 
('1','黄医生',2,'123456789123','放射科很厉害','121','11','1980-11-11'),
('10','郭医生',2,'15436767959','擅长呼吸科','121','11','1970-10-10'),
('11','欧阳医生',2,'12465647787','擅长呼吸科','121','11','1970-10-10'),
('2','杨医生',1,'13240370937','擅长呼吸科','121','11','1990-11-11'),
('3','蔡医生',1,'13250360890','擅长呼吸科','121','11','1990-09-18'),
('4','夏医生',1,'13378093424','擅长呼吸科','121','11','1980-08-12'),
('5','郑医生',1,'13423907854','擅长呼吸科','121','11','1970-05-23'),
('6','李医生',1,'13259087834','擅长呼吸科','121','11','1979-06-12'),
('7','尚医生',2,'14323546745','擅长呼吸科','121','11','1980-11-09'),
('8','刘医生',1,'13245557878','擅长呼吸道疾病','121','11','1980-11-10'),
('9','马医生',2,'12324357689','擅长呼吸道疾病','121','11','1970-12-10');

/*Table structure for table `guahao` */

DROP TABLE IF EXISTS `guahao`;

CREATE TABLE `guahao` (
  `guahao_id` int(32) NOT NULL AUTO_INCREMENT,
  `apart_id` varchar(32) DEFAULT NULL,
  `doctor_id` varchar(32) DEFAULT NULL,
  `patient_id` varchar(32) DEFAULT NULL,
  `guahao_biaozhi` int(1) DEFAULT NULL COMMENT '1为挂号，0已叫号',
  `huahao_date` datetime DEFAULT NULL,
  PRIMARY KEY (`guahao_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `guahao` */

/*Table structure for table `medicine` */

DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `m_id` varchar(32) NOT NULL,
  `m_name` varchar(25) NOT NULL,
  `m_attend` varchar(25) NOT NULL,
  `m_usage` varchar(25) NOT NULL,
  `m_price` double(10,2) NOT NULL,
  `m_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`m_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='药物表';

/*Data for the table `medicine` */

insert  into `medicine`(`m_id`,`m_name`,`m_attend`,`m_usage`,`m_price`,`m_img`) values 
('1','999感冒灵','治疗感冒,发烧','一天三次、一次一包',1.00,'image/medicine.jpg'),
('10','风热感冒颗粒','用于风热感冒、发热、有汗、鼻塞','口服，一次1袋，一日3次',22.00,'image/fr.jpg'),
('2','桑菊感冒丸（浓缩丸）','疏风清热，宣肺止咳。用于风热感冒初起，头痛，咳嗽','口服，每次25-30粒，一日2-3次',22.00,'image/sj.jpg'),
('3','保儿宁颗粒（黄金贝乐）','益气固表，健中醒脾。用于脾肺气虚所致的神倦纳呆','开水冲服。三岁以下，一次半袋：三岁以上，一次一袋',22.00,'image/baoern.jpg'),
('4','银柴颗粒','清热，解表，止咳。用于风热感冒，发热咳嗽','开水冲服，一次1袋，一日3-4次',22.00,'image/yckl.jpg'),
('5','藿香正气丸（浓缩丸）','解表化湿，理气和中。用于暑湿感冒，头痛身重胸闷','口服，一次8丸，一日3次',22.00,'image/hxzqw.jpg'),
('6','参苏丸','益气解表，疏风散寒，祛痰止咳。用于身体虚弱','口服。一次6～9克，一日2～3次',22.00,'image/cs.jpg'),
('7','复方板蓝根颗粒','清热解毒，凉血。用于风热感冒，咽喉肿痛','口服，一次15克，一日3次',22.00,'image/blgkeli.jpg'),
('8','小儿解感片','清热解表，消炎止咳。用于感冒发烧','口服，1至3岁小儿，一次 1片',22.00,'image/xiaoer.jpg'),
('9','暑湿感冒颗粒','清暑去湿，用于外感风寒引起的感冒','口服一日3次，一次1袋，小儿酌减',22.00,'image/ss.jpg');

/*Table structure for table `medicine_list` */

DROP TABLE IF EXISTS `medicine_list`;

CREATE TABLE `medicine_list` (
  `pre_id` varchar(32) NOT NULL,
  `m_id` varchar(32) NOT NULL,
  `num` varchar(10) NOT NULL,
  `usage` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='处方药物列表';

/*Data for the table `medicine_list` */

/*Table structure for table `menu` */

DROP TABLE IF EXISTS `menu`;

CREATE TABLE `menu` (
  `menuID` varchar(32) NOT NULL,
  `menuname` varchar(32) NOT NULL,
  `icon` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `PID` varchar(32) NOT NULL,
  PRIMARY KEY (`menuID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

insert  into `menu`(`menuID`,`menuname`,`icon`,`url`,`PID`) values 
('0','系统菜单','icon-sys','  ','  '),
('100','基础数据','icon-sys',' ','0'),
('101','科室管理','icon-sys','http://localhost:8080/omas.v.1.2/apartment','100'),
('102','医生管理','icon-sys','http://localhost:8080/omas.v.1.2/doctor','100'),
('103','病人管理','icon-sys','http://localhost:8080/omas.v.1.2/patient','100'),
('200','门诊信息','icon-sys',' ','0'),
('201','初诊管理','icon-sys','http://localhost:8080/omas.v.1.2/firstVisit','200'),
('300','系统管理','icon-sys',' ','0'),
('301','用户角色权限管理','icon-sys','http://localhost:8080/omas.v.1.2/UserRoleSet','300'),
('302','角色菜单权限管理','icon-sys','http://localhost:8080/omas.v.1.2/RoleMenuSet','300');

/*Table structure for table `patient` */

DROP TABLE IF EXISTS `patient`;

CREATE TABLE `patient` (
  `p_id` varchar(32) NOT NULL,
  `p_name` varchar(25) DEFAULT NULL,
  `p_birthday` date DEFAULT NULL,
  `p_sex` int(5) DEFAULT NULL,
  `p_address` varchar(255) DEFAULT NULL,
  `p_marriage` int(2) DEFAULT NULL,
  `p_phone` varchar(15) DEFAULT NULL,
  `p_email` varchar(25) DEFAULT NULL,
  `p_por` varchar(25) DEFAULT NULL,
  `card_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='病人表';

/*Data for the table `patient` */

insert  into `patient`(`p_id`,`p_name`,`p_birthday`,`p_sex`,`p_address`,`p_marriage`,`p_phone`,`p_email`,`p_por`,`card_id`) values 
('42F85155401F4835ADFDC377D9181D6E','陆99','1999-09-18',1,'佛山',1,'132409078651','32@qq.com','程序猿','23232413');

/*Table structure for table `prescription` */

DROP TABLE IF EXISTS `prescription`;

CREATE TABLE `prescription` (
  `pre_id` varchar(32) NOT NULL,
  `date` datetime NOT NULL,
  `fee` double(10,2) NOT NULL,
  `rec_id` varchar(32) NOT NULL,
  `pharmacy` varchar(25) NOT NULL,
  `zengduanResult` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pre_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='处方表';

/*Data for the table `prescription` */

/*Table structure for table `record` */

DROP TABLE IF EXISTS `record`;

CREATE TABLE `record` (
  `rec_id` varchar(32) NOT NULL,
  `p_id` varchar(32) NOT NULL,
  `doc_id` varchar(32) NOT NULL,
  `date` datetime NOT NULL,
  `status` int(5) NOT NULL,
  `desc_detail` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='病历表';

/*Data for the table `record` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `roleID` varchar(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`roleID`,`name`) values 
('1','系统管理员'),
('2','医生'),
('3','病人');

/*Table structure for table `role_menu` */

DROP TABLE IF EXISTS `role_menu`;

CREATE TABLE `role_menu` (
  `roleID` varchar(32) NOT NULL,
  `menuID` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

insert  into `role_menu`(`roleID`,`menuID`) values 
('2','200'),
('2','201'),
('2','202'),
('2','203'),
('3','200'),
('3','201'),
('3','202'),
('3','203'),
('3','300'),
('3','301'),
('3','302'),
('1','100'),
('1','101'),
('1','102'),
('1','103'),
('1','300'),
('1','301'),
('1','302');

/*Table structure for table `specialty` */

DROP TABLE IF EXISTS `specialty`;

CREATE TABLE `specialty` (
  `spe_id` varchar(32) NOT NULL,
  `doc_id` varchar(32) NOT NULL,
  `describe` varchar(25) NOT NULL,
  PRIMARY KEY (`spe_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='医生特长表';

/*Data for the table `specialty` */

/*Table structure for table `symptoms` */

DROP TABLE IF EXISTS `symptoms`;

CREATE TABLE `symptoms` (
  `sym_id` varchar(32) NOT NULL,
  `rec_id` varchar(32) NOT NULL,
  `desc` varchar(255) NOT NULL,
  `start_degree` varchar(255) DEFAULT NULL,
  `now_degree` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sym_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='症状表';

/*Data for the table `symptoms` */

/*Table structure for table `title` */

DROP TABLE IF EXISTS `title`;

CREATE TABLE `title` (
  `title_id` varchar(32) NOT NULL,
  `title_name` varchar(25) NOT NULL,
  PRIMARY KEY (`title_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='职称表';

/*Data for the table `title` */

insert  into `title`(`title_id`,`title_name`) values 
('120','主任主任'),
('121','副主任医师'),
('122','主治医师'),
('123','住院医师'),
('124','医士');

/*Table structure for table `tracking` */

DROP TABLE IF EXISTS `tracking`;

CREATE TABLE `tracking` (
  `des_id` varchar(32) NOT NULL,
  `p_id` varchar(32) NOT NULL,
  `rec_id` varchar(32) NOT NULL,
  `des_date` datetime NOT NULL,
  `symptom` text NOT NULL,
  `describe` varchar(25) DEFAULT NULL,
  `status` int(5) NOT NULL,
  PRIMARY KEY (`des_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日常记录表';

/*Data for the table `tracking` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `userID` varchar(32) NOT NULL,
  `userName` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `userDetailId` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`userID`,`userName`,`password`,`userDetailId`) values 
('1','admin','123',NULL),
('2','doctor','123','5');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `userID` varchar(32) NOT NULL,
  `roleID` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`userID`,`roleID`) values 
('1','1'),
('3','3'),
('2','2');

/*Table structure for table `visit_time` */

DROP TABLE IF EXISTS `visit_time`;

CREATE TABLE `visit_time` (
  `visit_id` varchar(32) NOT NULL,
  `doc_id` varchar(32) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`visit_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='坐诊表';

/*Data for the table `visit_time` */

/*Table structure for table `wxuser` */

DROP TABLE IF EXISTS `wxuser`;

CREATE TABLE `wxuser` (
  `wxID` varchar(32) DEFAULT NULL,
  `openid` varchar(100) DEFAULT NULL,
  `sessionKey` varchar(100) DEFAULT NULL,
  `token` varchar(100) DEFAULT NULL,
  `p_id` varchar(32) DEFAULT NULL,
  `tokenTime` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `wxuser` */

insert  into `wxuser`(`wxID`,`openid`,`sessionKey`,`token`,`p_id`,`tokenTime`) values 
('4554','445','55555','5555664','254879',NULL),
('42F85155401F4835ADFDC377D9181D6E','oh6cs5P2qAoBvhiUXjHHzN9WfGsQ','OPJLUfcWvJC4wCg3WsknrQ==','oh6cs5P2qAoBvhiUXjHHzN9WfGsQBFC0BF1E26FC4F4FAF4FF29C09253F5C','42F85155401F4835ADFDC377D9181D6E','2020-01-05 14:40:36');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
