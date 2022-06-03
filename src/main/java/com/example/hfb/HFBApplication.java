package com.example.hfb;

import com.example.hfb.entity.*;
import com.example.hfb.service.serviceimpl.SeedingService;
import com.example.hfb.utilities.Utilities;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNullApi;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Calendar;

@SpringBootApplication
public class HFBApplication {

    public static void main(String[] args) {
        SpringApplication.run(HFBApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner runner(SeedingService service) {
        return args -> {
//            // delete user_role
            service.deleteUserRoles();
//            service.resetIdUserRole();
//            // delete role
            service.deleteRoles();
//            service.resetIdRole();
//            // delete food
            service.deleteFoods();
//            service.resetIdFood();
//            // delete category
            service.deleteCategories();
//            service.resetIdCategory();
//            // delete user
            service.deleteUsers();
//            service.resetIdUser();

//            long datetime = Calendar.getInstance().getTimeInMillis();
//            // add role
//            service.saveRole(new Role(1, "ROLE_ADMIN"));
//            service.saveRole(new Role(2, "ROLE_USER"));
//
//           // add user
//            service.saveUser(new User(1, "admin", "admin@gmail.com", "123", "admin@gmail.com", "09061234", "Ha noi", "v1636104927/hanoi_food_bank_project/users_avatar/admin.jpg", 100, datetime, datetime, 1, 1, 1));
//            service.saveUser(new User(2, "Tran Hoang", "tlhoang1211@gmail.com", "123", "tlhoang1211@gmail.com", "09061234", "Ha noi", "v1636303335/hanoi_food_bank_project/users_avatar/user1.jpg", 100, datetime, datetime, 1, 1, 1));
//            service.saveUser(new User(3, "A", "test1@gmail.com", "123", "test1@gmail.com", "09061234", "Ha noi", "v1636105006/hanoi_food_bank_project/users_avatar/food%20donor.png", 100, datetime, datetime, 1, 1, 1));
//            service.saveUser(new User(4, "B", "test2@gmail.com", "123", "test2@gmail.com", "09061234", "Ha noi", "v1636104837/hanoi_food_bank_project/users_avatar/volunteer.png", 100, datetime, datetime, 1, 1, 1));
//            service.saveUser(new User(5, "C", "test3@gmail.com", "123", "test3@gmail.com", "09061234", "Ha noi", "v1636105076/hanoi_food_bank_project/users_avatar/recipient.jpg", 100, datetime, datetime, 1, 1, 1));
//
//            //add user_role
//            service.addRoleToUser("admin@gmail.com", "ROLE_ADMIN");
//            service.addRoleToUser("tlhoang1211@gmail.com", "ROLE_USER");
//            service.addRoleToUser("test1@gmail.com", "ROLE_USER");
//            service.addRoleToUser("test2@gmail.com", "ROLE_USER");
//            service.addRoleToUser("test3@gmail.com", "ROLE_USER");
//
//            // add category
//            service.saveCategory(1, 2, "Drinks");
//            service.saveCategory(2, 2, "Noodle");
//            service.saveCategory(3, 2, "Bread");
//            service.saveCategory(4, 2, "Rice");
//            service.saveCategory(5, 2, "Meat");
//            service.saveCategory(6, 2, "Seafood");
//            service.saveCategory(7, 2, "Vegetables");
//            service.saveCategory(8, 2, "Vegetarian Food");
//            service.saveCategory(9, 2, "Fruit");
//            service.saveCategory(10, 2, "Fast Food");
//            service.saveCategory(11, 2, "Snacks");
//            service.saveCategory(12, 2, "Other");
//
//            // drink
//            service.saveFood(new Food(1,"Trà sữa","v1636265587/hanoi_food_bank_project/uploaded_food/Drinks/trasua1_hodocl.jpg",
//                    "v1636265587/hanoi_food_bank_project/uploaded_food/Drinks/trasua1_hodocl.jpg,v1636265586/hanoi_food_bank_project/uploaded_food/Drinks/trasua_z7eyfo.jpg",
//                    "Trà sữa ", "còn 1 suất duy nhất",
//                    Utilities.convertStringToLong("2022-08-04"), Utilities.convertStringToLong("2022-08-01"), Utilities.convertStringToLong("2022-08-01"), 4, 4,
//                    service.findByCategoryId(1),
//                    1)
//            );
//            service.saveFood(new Food(2,"Nước ép trái cây","v1636266037/hanoi_food_bank_project/uploaded_food/Drinks/nuocep_fgftoc.jpg",
//                    "v1636266037/hanoi_food_bank_project/uploaded_food/Drinks/nuocep_fgftoc.jpg,v1636266036/hanoi_food_bank_project/uploaded_food/Drinks/nuocep1_owvhde.jpg",
//                    "Nước ép trái cây", "mọi người nhanh tay",
//                    Utilities.convertStringToLong("2022-08-05"), Utilities.convertStringToLong("2022-08-02"), Utilities.convertStringToLong("2022-08-02"), 4, 4,
//                    service.findByCategoryId(1),
//                    1)
//            );
//            service.saveFood(new Food(3,"Nước ép rau má","1636267616/hanoi_food_bank_project/uploaded_food/Drinks/rauma1_u1z0ar.jpg",
//                    "1636267616/hanoi_food_bank_project/uploaded_food/Drinks/rauma1_u1z0ar.jpg,v1636267708/hanoi_food_bank_project/uploaded_food/Drinks/rauma_rg6r2a.jpg",
//                    "Nước ép đậu xanh rau má tốt cho sức khỏe, thanh lọc cơ thể", "5 suất cho những chiếc bụng đói",
//                    Utilities.convertStringToLong("2022-08-06"), Utilities.convertStringToLong("2022-08-03"), Utilities.convertStringToLong("2022-08-03"), 4, 4,
//                    service.findByCategoryId(1),
//                    1)
//            );
//            service.saveFood(new Food(4,"Chè thập cẩm","v1636267824/hanoi_food_bank_project/uploaded_food/Drinks/chethapcam_tmcqbj.jpg",
//                    "v1636267824/hanoi_food_bank_project/uploaded_food/Drinks/chethapcam_tmcqbj.jpg,v1636267825/hanoi_food_bank_project/uploaded_food/Drinks/chebuoi_hxkpsz.jpg",
//                    "Các món chè truyền thống, chè thập cẩm, chè bưởi ....", "mọi người liên hệ ngay",
//                    Utilities.convertStringToLong("2022-08-07"), Utilities.convertStringToLong("2022-08-04"), Utilities.convertStringToLong("2022-08-04"), 4, 4,
//                    service.findByCategoryId(1),
//                    1)
//            );
//            service.saveFood(new Food(5,"Trà chanh","v1636268040/hanoi_food_bank_project/uploaded_food/Drinks/trachanh_m6ywid.jpg",
//                    "v1636268040/hanoi_food_bank_project/uploaded_food/Drinks/trachanh_m6ywid.jpg,v1636268030/hanoi_food_bank_project/uploaded_food/Drinks/trachanh1_riebv8.jpg",
//                    "Trà chanh thanh mát", "lấy ngay chiều nay",
//                    Utilities.convertStringToLong("2022-08-08"), Utilities.convertStringToLong("2022-08-05"), Utilities.convertStringToLong("2022-08-05"), 4, 4,
//                    service.findByCategoryId(1),
//                    1)
//            );
//            service.saveFood(new Food(6,"Cafe","v1636268227/hanoi_food_bank_project/uploaded_food/Drinks/cafe1_u1fnuv.jpg",
//                    "v1636268227/hanoi_food_bank_project/uploaded_food/Drinks/cafe1_u1fnuv.jpg",
//                    "Cafe đen, cafe nâu đen", "dành cho các bạn đang cần ngay",
//                    Utilities.convertStringToLong("2022-08-09"), Utilities.convertStringToLong("2022-08-06"), Utilities.convertStringToLong("2022-08-06"), 4, 4,
//                    service.findByCategoryId(1),
//                    1)
//            );
//            service.saveFood(new Food(7,"Sinh tố trái cây","v1636268371/hanoi_food_bank_project/uploaded_food/Drinks/sinhto1_ogfaph.jpg",
//                    "v1636268371/hanoi_food_bank_project/uploaded_food/Drinks/sinhto1_ogfaph.jpg",
//                    "Sinh tố trái cây nhiệt đới", "nhanh tay khi còn nóng hổi",
//                    Utilities.convertStringToLong("2022-08-10"), Utilities.convertStringToLong("2022-08-07"), Utilities.convertStringToLong("2022-08-07"), 4, 4,
//                    service.findByCategoryId(1),
//                    1)
//            );
//            service.saveFood(new Food(8,"Dâu tây kem đá xay","v1636268570/hanoi_food_bank_project/uploaded_food/Drinks/daxay1_a7zor3.jpg",
//                    "v1636268570/hanoi_food_bank_project/uploaded_food/Drinks/daxay1_a7zor3.jpg,v1636268572/hanoi_food_bank_project/uploaded_food/Drinks/daxay_xptczs.jpg",
//                    "Dâu tây đá xay kem chesse", "cho các bạn cần trưa nay",
//                    Utilities.convertStringToLong("2022-08-11"), Utilities.convertStringToLong("2022-08-08"), Utilities.convertStringToLong("2022-08-08"), 4, 4,
//                    service.findByCategoryId(1),
//                    1)
//            );
//            service.saveFood(new Food(9,"Trà dâu ","v1636268883/hanoi_food_bank_project/uploaded_food/Drinks/tradau_akvtqo.jpg",
//                    "v1636268883/hanoi_food_bank_project/uploaded_food/Drinks/tradau_akvtqo.jpg,v1636268884/hanoi_food_bank_project/uploaded_food/Drinks/tradau1_fl0s8m.jpg",
//                    "Trà dâu tằm thơm ngon", "nhanh tay chỉ trong tối nay",
//                    Utilities.convertStringToLong("2022-08-12"), Utilities.convertStringToLong("2022-08-09"), Utilities.convertStringToLong("2022-08-09"), 4, 4,
//                    service.findByCategoryId(1),
//                    1)
//            );
//            service.saveFood(new Food(10,"Sữa đậu nành","v1636268998/hanoi_food_bank_project/uploaded_food/Drinks/suadau_majfsi.jpg",
//                    "v1636268998/hanoi_food_bank_project/uploaded_food/Drinks/suadau_majfsi.jpg,v1636268996/hanoi_food_bank_project/uploaded_food/Drinks/suadau1_udj3ln.jpg",
//                    "Sữa đậu nành thơm ngon với nhiều hương vị: lá dứa, khoai môn, việt quất,...", "mọi người nhanh tay",
//                    Utilities.convertStringToLong("2022-08-13"), Utilities.convertStringToLong("2022-08-10"), Utilities.convertStringToLong("2022-08-10"), 4, 4,
//                    service.findByCategoryId(1),
//                    1)
//            );
//
//
//
//
//
//            // noodle
//            service.saveFood(new Food(11,"Bún chả","v1636248349/hanoi_food_bank_project/uploaded_food/Noodle/buncha_qy0fhc.jpg",
//                    "v1636248349/hanoi_food_bank_project/uploaded_food/Noodle/buncha_qy0fhc.jpg,v1636248356/hanoi_food_bank_project/uploaded_food/Noodle/buncha1_h7apju.jpg",
//                    "bún chả ngon", "còn 1 suất duy nhất",
//                    Utilities.convertStringToLong("2022-08-08"), Utilities.convertStringToLong("2022-08-05"), Utilities.convertStringToLong("2022-08-05"), 4, 4,
//                    service.findByCategoryId(2),
//                    2)
//            );
//            service.saveFood(new Food(12,"Bún miến ngan","v1636248386/hanoi_food_bank_project/uploaded_food/Noodle/bunmienngan_vm7esy.jpg",
//                    "v1636248386/hanoi_food_bank_project/uploaded_food/Noodle/bunmienngan_vm7esy.jpg,v1636248386/hanoi_food_bank_project/uploaded_food/Noodle/bunmienngan1_n35cbb.jpg",
//                    "bún ngan nhiều thịt", "mọi người nhanh tay",
//                    Utilities.convertStringToLong("2022-08-09"), Utilities.convertStringToLong("2022-08-06"), Utilities.convertStringToLong("2022-08-06"), 4, 4,
//                    service.findByCategoryId(2),
//                    2)
//            );
//            service.saveFood(new Food(13,"Bún bò huế","v1636248294/hanoi_food_bank_project/uploaded_food/Noodle/bunbohue_v3ohyl.jpg",
//                    "v1636248294/hanoi_food_bank_project/uploaded_food/Noodle/bunbohue_v3ohyl.jpg,v1636248312/hanoi_food_bank_project/uploaded_food/Noodle/bunbohue1_ffvix7.jpg",
//                    "đậm đà hương vị", "5 suất cho những chiếc bụng đói",
//                    Utilities.convertStringToLong("2022-08-10"), Utilities.convertStringToLong("2022-08-07"), Utilities.convertStringToLong("2022-08-07"), 4, 4,
//                    service.findByCategoryId(2),
//                    2)
//            );
//            service.saveFood(new Food(14,"Phở cuốn","v1636248386/hanoi_food_bank_project/uploaded_food/Noodle/phocuon_xn6r2l.jpg",
//                    "v1636248386/hanoi_food_bank_project/uploaded_food/Noodle/phocuon_xn6r2l.jpg,v1636248386/hanoi_food_bank_project/uploaded_food/Noodle/phocuon1_zbcm6j.jpg",
//                    "thị heo cùng gia vị đầy ngất ngây", "mọi người liên hệ ngay",
//                    Utilities.convertStringToLong("2022-08-11"), Utilities.convertStringToLong("2022-08-08"), Utilities.convertStringToLong("2022-08-08"), 4, 4,
//                    service.findByCategoryId(2),
//                    2)
//            );
//            service.saveFood(new Food(15,"Bún riêu bề bề","v1636248383/hanoi_food_bank_project/uploaded_food/Noodle/bunrieubebe_nvviro.jpg",
//                    "v1636248383/hanoi_food_bank_project/uploaded_food/Noodle/bunrieubebe_nvviro.jpg,v1636248385/hanoi_food_bank_project/uploaded_food/Noodle/bunrieubebe1_snobmi.jpg",
//                    "cay cay, ngọt vị hải sản", "lấy ngay chiều nay",
//                    Utilities.convertStringToLong("2022-08-12"), Utilities.convertStringToLong("2022-08-09"), Utilities.convertStringToLong("2022-08-09"), 4, 4,
//                    service.findByCategoryId(2),
//                    2)
//            );
//            service.saveFood(new Food(16,"Bún cá","v1636248323/hanoi_food_bank_project/uploaded_food/Noodle/bunca_j8gmpc.jpg",
//                    "v1636248323/hanoi_food_bank_project/uploaded_food/Noodle/bunca_j8gmpc.jpg,v1636248328/hanoi_food_bank_project/uploaded_food/Noodle/bunca1_cjleeh.jpg",
//                    "đặc sản bún cá chuẩn mẹ nấu", "dành cho các bạn đang cần ngay",
//                    Utilities.convertStringToLong("2022-08-13"), Utilities.convertStringToLong("2022-08-10"), Utilities.convertStringToLong("2022-08-10"), 4, 4,
//                    service.findByCategoryId(2),
//                    2)
//            );
//            service.saveFood(new Food(17,"Phở bò","v1636248385/hanoi_food_bank_project/uploaded_food/Noodle/phobo_scqxt7.jpg",
//                    "v1636248385/hanoi_food_bank_project/uploaded_food/Noodle/phobo_scqxt7.jpg,v1636248385/hanoi_food_bank_project/uploaded_food/Noodle/phobo1_didccx.jpg",
//                    "đậm vị hà nội", "nhanh tay khi còn nóng hổi",
//                    Utilities.convertStringToLong("2022-08-14"), Utilities.convertStringToLong("2022-08-11"), Utilities.convertStringToLong("2022-08-11"), 4, 4,
//                    service.findByCategoryId(2),
//                    2)
//            );
//            service.saveFood(new Food(18,"Bún thang","v1636248385/hanoi_food_bank_project/uploaded_food/Noodle/bunthang_sgrcce.jpg",
//                    "v1636248385/hanoi_food_bank_project/uploaded_food/Noodle/bunthang_sgrcce.jpg,v1636248385/hanoi_food_bank_project/uploaded_food/Noodle/bunthang1_qo1tee.jpg",
//                    "nước dùng ngọn thanh", "cho các bạn cần trưa nay",
//                    Utilities.convertStringToLong("2022-08-15"), Utilities.convertStringToLong("2022-08-12"), Utilities.convertStringToLong("2022-08-12"), 4, 4,
//                    service.findByCategoryId(2),
//                    2)
//            );
//            service.saveFood(new Food(19,"Mì ý","v1636248384/hanoi_food_bank_project/uploaded_food/Noodle/myy_vevfde.jpg",
//                    "v1636248384/hanoi_food_bank_project/uploaded_food/Noodle/myy_vevfde.jpg,v1636248385/hanoi_food_bank_project/uploaded_food/Noodle/myy2_lb1fgp.jpg",
//                    "hương vị châu âu", "nhanh tay chỉ trong tối nay",
//                    Utilities.convertStringToLong("2022-08-16"), Utilities.convertStringToLong("2022-08-13"), Utilities.convertStringToLong("2022-08-13"), 4, 4,
//                    service.findByCategoryId(2),
//                    2)
//            );
//            service.saveFood(new Food(20,"Mì tương đen","v1636248384/hanoi_food_bank_project/uploaded_food/Noodle/mituongden_zxsiua.jpg",
//                    "v1636248384/hanoi_food_bank_project/uploaded_food/Noodle/mituongden_zxsiua.jpg,v1636248385/hanoi_food_bank_project/uploaded_food/Noodle/mituongden1_lmg2yl.jpg",
//                    "hương vị hàn quốc", "mọi người nhanh tay",
//                    Utilities.convertStringToLong("2022-08-17"), Utilities.convertStringToLong("2022-08-14"), Utilities.convertStringToLong("2022-14-10"), 4, 4,
//                    service.findByCategoryId(2),
//                    2)
//            );
//
//
//            // bread
//            service.saveFood(new Food(21,"Bánh mỳ heo quay","v1636274435/hanoi_food_bank_project/uploaded_food/Bread/banhmykepthi_hjlftf.jpg",
//                    "v1636274435/hanoi_food_bank_project/uploaded_food/Bread/banhmykepthi_hjlftf.jpg,v1636274433/hanoi_food_bank_project/uploaded_food/Bread/banhmykepthi1_x0um0l.jpg",
//                    "Bánh mỳ nhân heo quay , thơm ngon giòn dụm", "còn 1 suất duy nhất",
//                    Utilities.convertStringToLong("2022-08-05"), Utilities.convertStringToLong("2022-08-02"), Utilities.convertStringToLong("2022-08-02"), 4, 4,
//                    service.findByCategoryId(3),
//                    3)
//            );
//            service.saveFood(new Food(22,"Bánh mỳ Doner Kebab","v1636274548/hanoi_food_bank_project/uploaded_food/Bread/doner_audpfx.jpg",
//                    "v1636274548/hanoi_food_bank_project/uploaded_food/Bread/doner_audpfx.jpg,v1636274547/hanoi_food_bank_project/uploaded_food/Bread/doner1_zies4c.jpg",
//                    "Bánh mỳ mang hương vị Thổ Nhĩ Kỳ", "mọi người nhanh tay",
//                    Utilities.convertStringToLong("2022-08-06"), Utilities.convertStringToLong("2022-08-03"), Utilities.convertStringToLong("2022-08-03"), 4, 4,
//                    service.findByCategoryId(3),
//                    3)
//            );
//            service.saveFood(new Food(23,"Bánh mỳ sốt vang","v1636274713/hanoi_food_bank_project/uploaded_food/Bread/banhmiotvang1_l1isew.jpg",
//                    "v1636274713/hanoi_food_bank_project/uploaded_food/Bread/banhmiotvang1_l1isew.jpg,v1636274712/hanoi_food_bank_project/uploaded_food/Bread/banhmiotvang_s2xlet.jpg",
//                    "đậm đà hương vị", "5 suất cho những chiếc bụng đói",
//                    Utilities.convertStringToLong("2022-08-07"), Utilities.convertStringToLong("2022-08-04"), Utilities.convertStringToLong("2022-08-04"), 4, 4,
//                    service.findByCategoryId(3),
//                    3)
//            );
//            service.saveFood(new Food(24,"Bánh mỳ chảo","v1636274930/hanoi_food_bank_project/uploaded_food/Bread/banhmychao_kw6pm0.jpg",
//                    "v1636274930/hanoi_food_bank_project/uploaded_food/Bread/banhmychao_kw6pm0.jpg,v1636274929/hanoi_food_bank_project/uploaded_food/Bread/banhmychao1_zw7jy7.jpg",
//                    "Hương vị đầy ngất ngây", "mọi người liên hệ ngay",
//                    Utilities.convertStringToLong("2022-08-08"), Utilities.convertStringToLong("2022-08-04"), Utilities.convertStringToLong("2022-08-04"), 4, 4,
//                    service.findByCategoryId(3),
//                    3)
//            );
//            service.saveFood(new Food(25,"Bánh mỳ nướng Lạng Sơn","v1636277512/hanoi_food_bank_project/uploaded_food/Bread/banhmynuong1_vylmr8.jpg",
//                    "v1636277512/hanoi_food_bank_project/uploaded_food/Bread/banhmynuong1_vylmr8.jpg,v1636277513/hanoi_food_bank_project/uploaded_food/Bread/banhmynuong_hbyf1g.jpg",
//                    "Ngọt vị mật ong", "lấy ngay chiều nay",
//                    Utilities.convertStringToLong("2022-08-09"), Utilities.convertStringToLong("2022-08-05"), Utilities.convertStringToLong("2022-08-05"), 4, 4,
//                    service.findByCategoryId(3),
//                    3)
//            );
//            service.saveFood(new Food(26,"Bánh mỳ que","v1636248323/hanoi_food_bank_project/uploaded_food/Noodle/bunca_j8gmpc.jpg",
//                    "v1636248323/hanoi_food_bank_project/uploaded_food/Noodle/bunca_j8gmpc.jpg,v1636248328/hanoi_food_bank_project/uploaded_food/Noodle/bunca1_cjleeh.jpg",
//                    "Đặc sản Hải Phòng", "dành cho các bạn đang cần ngay",
//                    Utilities.convertStringToLong("2022-08-10"), Utilities.convertStringToLong("2022-08-06"), Utilities.convertStringToLong("2022-08-06"), 4, 4,
//                    service.findByCategoryId(3),
//                    3)
//            );
//            service.saveFood(new Food(27,"Pizza","v1636277830/hanoi_food_bank_project/uploaded_food/Bread/pizza_sdhvi4.jpg",
//                    "v1636277830/hanoi_food_bank_project/uploaded_food/Bread/pizza_sdhvi4.jpg,v1636277829/hanoi_food_bank_project/uploaded_food/Bread/pizza1_dsyv8n.jpg",
//                    "Hương vị đậm đà", "nhanh tay khi còn nóng hổi",
//                    Utilities.convertStringToLong("2022-08-11"), Utilities.convertStringToLong("2022-08-07"), Utilities.convertStringToLong("2022-08-07"), 4, 4,
//                    service.findByCategoryId(3),
//                    3)
//            );
//            service.saveFood(new Food(28,"Bánh bông lan trứng muối","v1636277977/hanoi_food_bank_project/uploaded_food/Bread/bonglantrungmuoi_ildfpb.jpg",
//                    "v1636277977/hanoi_food_bank_project/uploaded_food/Bread/bonglantrungmuoi_ildfpb.jpg,v1636277978/hanoi_food_bank_project/uploaded_food/Bread/bonglantrungmuoi1_knz860.jpg",
//                    "Mệm xốp thơm ngon", "cho các bạn cần trưa nay",
//                    Utilities.convertStringToLong("2022-08-12"), Utilities.convertStringToLong("2022-08-08"), Utilities.convertStringToLong("2022-08-08"), 4, 4,
//                    service.findByCategoryId(3),
//                    3)
//            );
//            service.saveFood(new Food(29,"Bánh cupcake","v1636278414/hanoi_food_bank_project/uploaded_food/Bread/banhkem_fajzjl.jpg",
//                    "v1636278414/hanoi_food_bank_project/uploaded_food/Bread/banhkem_fajzjl.jpg,v1636278416/hanoi_food_bank_project/uploaded_food/Bread/banhkem1_oxli1r.jpg",
//                    "hương vị châu âu", "nhanh tay chỉ trong tối nay",
//                    Utilities.convertStringToLong("2022-08-13"), Utilities.convertStringToLong("2022-08-09"), Utilities.convertStringToLong("2022-08-09"), 4, 4,
//                    service.findByCategoryId(3),
//                    3)
//            );
//            service.saveFood(new Food(30,"Bánh sừng bò","v1636278650/hanoi_food_bank_project/uploaded_food/Bread/banhsungbo_m9vj06.jpg",
//                    "v1636278650/hanoi_food_bank_project/uploaded_food/Bread/banhsungbo_m9vj06.jpg,v1636278648/hanoi_food_bank_project/uploaded_food/Bread/banhsungbo1_aqq1q5.jpg",
//                    "Vị bơ , phô mai, thịt nguội", "mọi người nhanh tay",
//                    Utilities.convertStringToLong("2022-08-14"), Utilities.convertStringToLong("2022-08-10"), Utilities.convertStringToLong("2022-08-10"), 4, 4,
//                    service.findByCategoryId(3),
//                    3)
//            );
//
//
//            // rice
//            service.saveFood(new Food(31,"Cơm gà chiên nước mắm","v1636269287/hanoi_food_bank_project/uploaded_food/Rice/comganuocmam_shcxyn.jpg",
//                    "v1636269287/hanoi_food_bank_project/uploaded_food/Rice/comganuocmam_shcxyn.jpg,v1636269286/hanoi_food_bank_project/uploaded_food/Rice/comganuocmam_1_ucbvby.jpg",
//                    "Cơm gà chiên mắm thơm ngon", "còn 1 suất duy nhất",
//                    Utilities.convertStringToLong("2022-08-03"), Utilities.convertStringToLong("2022-09-29"), Utilities.convertStringToLong("2022-09-29"), 4, 4,
//                    service.findByCategoryId(4),
//                    4)
//            );
//            service.saveFood(new Food(32,"Cơm tấm","v1636269444/hanoi_food_bank_project/uploaded_food/Rice/comtam_ghewsm.jpg",
//                    "v1636269444/hanoi_food_bank_project/uploaded_food/Rice/comtam_ghewsm.jpg,v1636269570/hanoi_food_bank_project/uploaded_food/Rice/comtam1_cjqbxd.jpg",
//                    "Cơm sườn tấm Sài Gòn", "mọi người nhanh tay",
//                    Utilities.convertStringToLong("2022-08-04"), Utilities.convertStringToLong("2022-08-01"), Utilities.convertStringToLong("2022-08-01"), 4, 4,
//                    service.findByCategoryId(4),
//                    4)
//            );
//            service.saveFood(new Food(33,"Cơm rang","v1636269710/hanoi_food_bank_project/uploaded_food/Rice/conrang_gnhoer.jpg",
//                    "v1636269710/hanoi_food_bank_project/uploaded_food/Rice/conrang_gnhoer.jpg,v1636269710/hanoi_food_bank_project/uploaded_food/Rice/comrang1_e98rkk.jpg",
//                    "Cơm rang thập cẩm, cơm rang dưa bò", "5 suất cho những chiếc bụng đói",
//                    Utilities.convertStringToLong("2022-08-05"), Utilities.convertStringToLong("2022-08-02"), Utilities.convertStringToLong("2022-08-02"), 4, 4,
//                    service.findByCategoryId(4),
//                    4)
//            );
//            service.saveFood(new Food(34,"Cơm văn phòng","v1636269956/hanoi_food_bank_project/uploaded_food/Rice/comvanphong1_b9ej19.jpg",
//                    "v1636269956/hanoi_food_bank_project/uploaded_food/Rice/comvanphong1_b9ej19.jpg,v1636269956/hanoi_food_bank_project/uploaded_food/Rice/comvanphong_lpj9la.jpg",
//                    "Cơm trưa văn phòng", "mọi người liên hệ ngay",
//                    Utilities.convertStringToLong("2022-08-06"), Utilities.convertStringToLong("2022-08-03"), Utilities.convertStringToLong("2022-08-03"), 4, 4,
//                    service.findByCategoryId(4),
//                    4)
//            );
//            service.saveFood(new Food(35,"Cơm thố","v1636270153/hanoi_food_bank_project/uploaded_food/Rice/comtho_whwlwa.jpg",
//                    "v1636270153/hanoi_food_bank_project/uploaded_food/Rice/comtho_whwlwa.jpg,v1636270153/hanoi_food_bank_project/uploaded_food/Rice/comtho1_hmdpqa.jpg",
//                    "Cơm thố singapo", "lấy ngay chiều nay",
//                    Utilities.convertStringToLong("2022-08-07"), Utilities.convertStringToLong("2022-08-04"), Utilities.convertStringToLong("2022-08-04"), 4, 4,
//                    service.findByCategoryId(4),
//                    4)
//            );
//            service.saveFood(new Food(36,"Cơm trộn","v1636270259/hanoi_food_bank_project/uploaded_food/Rice/comtron1_csc9ez.jpg",
//                    "v1636270259/hanoi_food_bank_project/uploaded_food/Rice/comtron1_csc9ez.jpg,v1636270259/hanoi_food_bank_project/uploaded_food/Rice/comtron_oewfpb.jpg",
//                    "Cơm trộn Hàn Quốc", "dành cho các bạn đang cần ngay",
//                    Utilities.convertStringToLong("2022-08-08"), Utilities.convertStringToLong("2022-08-05"), Utilities.convertStringToLong("2022-08-05"), 4, 4,
//                    service.findByCategoryId(4),
//                    4)
//            );
//            service.saveFood(new Food(37,"Cơm cuộn","v1636270462/hanoi_food_bank_project/uploaded_food/Rice/comcuon1_nahocj.jpg",
//                    "v1636270462/hanoi_food_bank_project/uploaded_food/Rice/comcuon1_nahocj.jpg",
//                    "Hương vị độc đáo", "nhanh tay khi còn nóng hổi",
//                    Utilities.convertStringToLong("2022-08-09"), Utilities.convertStringToLong("2022-08-06"), Utilities.convertStringToLong("2022-08-06"), 4, 4,
//                    service.findByCategoryId(4),
//                    4)
//            );
//            service.saveFood(new Food(38,"Cơm gạo lứt","v1636270681/hanoi_food_bank_project/uploaded_food/Rice/comgaolut_n4eptw.jpg",
//                    "v1636270681/hanoi_food_bank_project/uploaded_food/Rice/comgaolut_n4eptw.jpg,v1636270680/hanoi_food_bank_project/uploaded_food/Rice/comgaolut1_hfgk20.jpg",
//                    "Tốt cho sức khỏe", "cho các bạn cần trưa nay",
//                    Utilities.convertStringToLong("2022-08-10"), Utilities.convertStringToLong("2022-08-07"), Utilities.convertStringToLong("2022-08-07"), 4, 4,
//                    service.findByCategoryId(4),
//                    4)
//            );
//            service.saveFood(new Food(39,"Cơm chay","v1636270886/hanoi_food_bank_project/uploaded_food/Rice/comchay_znw61z.jpg",
//                    "v1636270886/hanoi_food_bank_project/uploaded_food/Rice/comchay_znw61z.jpg,v1636270886/hanoi_food_bank_project/uploaded_food/Rice/comchay1_kcrht8.jpg",
//                    "Cơm chay Việt", "nhanh tay chỉ trong tối nay",
//                    Utilities.convertStringToLong("2022-08-11"), Utilities.convertStringToLong("2022-08-08"), Utilities.convertStringToLong("2022-08-08"), 4, 4,
//                    service.findByCategoryId(4),
//                    4)
//            );
//            service.saveFood(new Food(40,"Cơm niêu","v1636274184/hanoi_food_bank_project/uploaded_food/Rice/comnieu_jktywr.jpg",
//                    "v1636274184/hanoi_food_bank_project/uploaded_food/Rice/comnieu_jktywr.jpg,v1636274184/hanoi_food_bank_project/uploaded_food/Rice/comnieu1_s5wbjz.jpg",
//                    "Cơm niêu chuẩn vị Singapo", "mọi người nhanh tay",
//                    Utilities.convertStringToLong("2022-08-12"), Utilities.convertStringToLong("2022-08-09"), Utilities.convertStringToLong("2022-08-09"), 4, 4,
//                    service.findByCategoryId(4),
//                    4)
//            );
//            service.saveFood(new Food(
//                    41,
//                    "Khoai Tây Xào Thịt Bò",
//                    "v1636221484/hanoi_food_bank_project/uploaded_food/Meat/khoai_tay_xao_thit_bo_2.jpg",
//                    "v1636221489/hanoi_food_bank_project/uploaded_food/Meat/khoai_tay_xao_thit_bo_1.jpg,v1636221484/hanoi_food_bank_project/uploaded_food/Meat/khoai_tay_xao_thit_bo_2.jpg",
//                    "Khoai tây xào thịt bò nóng hổi đây",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-17"),
//                    Utilities.convertStringToLong("2022-08-15"),
//                    Utilities.convertStringToLong("2022-08-15"),
//                    3,
//                    3,
//                    service.findByCategoryId(5),
//                    5));
//
//            service.saveFood(new Food(
//                    42,
//                    "Thịt Dăm Heo Kho Củ Cải Trắng Và Cà Rốt",
//                    "v1636249112/hanoi_food_bank_project/uploaded_food/Meat/thit_dam_heo_kho_cu_cai_trang_va_ca_rot_1.jpg",
//                    "v1636249112/hanoi_food_bank_project/uploaded_food/Meat/thit_dam_heo_kho_cu_cai_trang_va_ca_rot_1.jpg,v1636249109/hanoi_food_bank_project/uploaded_food/Meat/thit_dam_heo_kho_cu_cai_trang_va_ca_rot_2.jpg",
//                    "Nóng hổi đây",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-18"),
//                    Utilities.convertStringToLong("2022-08-16"),
//                    Utilities.convertStringToLong("2022-08-16"),
//                    3,
//                    3,
//                    service.findByCategoryId(5),
//                    5));
//
//            service.saveFood(new Food(
//                    43,
//                    "Thịt Chưng Mắm Tôm",
//                    "v1636249722/hanoi_food_bank_project/uploaded_food/Meat/thit_chung_mam_tom_1.jpg",
//                    "v1636249722/hanoi_food_bank_project/uploaded_food/Meat/thit_chung_mam_tom_1.jpg,v1636249728/hanoi_food_bank_project/uploaded_food/Meat/thit_chung_mam_tom_2.jpg",
//                    "Thịt chưng mắm tôm lạ mà dễ ăn",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-19"),
//                    Utilities.convertStringToLong("2022-08-17"),
//                    Utilities.convertStringToLong("2022-08-17"),
//                    3,
//                    3,
//                    service.findByCategoryId(5),
//                    5));
//
//            service.saveFood(new Food(
//                    44,
//                    "Thịt Ba Rọi Rim Bắp Sốt Bơ",
//                    "v1636250237/hanoi_food_bank_project/uploaded_food/Meat/thit_ba_roi_rim_bap_sot_bo_1.jpg",
//                    "v1636250237/hanoi_food_bank_project/uploaded_food/Meat/thit_ba_roi_rim_bap_sot_bo_1.jpg,v1636250245/hanoi_food_bank_project/uploaded_food/Meat/thit_ba_roi_rim_bap_sot_bo_2.jpg",
//                    "Nóng hổi đây",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-20"),
//                    Utilities.convertStringToLong("2022-08-18"),
//                    Utilities.convertStringToLong("2022-08-18"),
//                    3,
//                    3,
//                    service.findByCategoryId(5),
//                    5));
//
//            service.saveFood(new Food(
//                    45,
//                    "Khoai Tây Xào Thịt Bò",
//                    "v1636250498/hanoi_food_bank_project/uploaded_food/Meat/thit_luoc_cham_nuoc_mam_toi_ot_1.jpg",
//                    "v1636250498/hanoi_food_bank_project/uploaded_food/Meat/thit_luoc_cham_nuoc_mam_toi_ot_1.jpg ,v1636250508/hanoi_food_bank_project/uploaded_food/Meat/thit_luoc_cham_nuoc_mam_toi_ot_2.jpg",
//                    "Nóng hổi đây",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-21"),
//                    Utilities.convertStringToLong("2022-08-19"),
//                    Utilities.convertStringToLong("2022-08-19"),
//                    3,
//                    3,
//                    service.findByCategoryId(5),
//                    5));
//
//            service.saveFood(new Food(
//                    46,
//                    "Ba Chỉ Bò",
//                    "v1636251906/hanoi_food_bank_project/uploaded_food/Meat/ba_chi_bo_1.jpg",
//                    "v1636251906/hanoi_food_bank_project/uploaded_food/Meat/ba_chi_bo_1.jpg,v1636251918/hanoi_food_bank_project/uploaded_food/Meat/ba_chi_bo_2.jpg,v1636252056/hanoi_food_bank_project/uploaded_food/Meat/ba_chi_bo_3.jpg",
//                    "Ba chỉ bò Mỹ,Thịt nhập khẩu",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-25"),
//                    Utilities.convertStringToLong("2022-08-20"),
//                    Utilities.convertStringToLong("2022-08-20"),
//                    3,
//                    3,
//                    service.findByCategoryId(5),
//                    5));
//
//            service.saveFood(new Food(
//                    47,
//                    "Thăn Vai Bò Úc",
//                    "v1636252182/hanoi_food_bank_project/uploaded_food/Meat/than_vai_bo_uc_1.jpg",
//                    "v1636252197/hanoi_food_bank_project/uploaded_food/Meat/than_vai_bo_uc_1.jpg,v1636252197/hanoi_food_bank_project/uploaded_food/Meat/than_vai_bo_uc_2.png",
//                    "Thăn vai bò Úc của BeefDaily thái lát mỏng thích hợp với các món lẩu,xào,nấu,nướng nhanh và thăn vai cắt 1cm-3cm thường được sử dụng cho món bò nướng,Beefsteak...",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-26"),
//                    Utilities.convertStringToLong("2022-08-21"),
//                            Utilities.convertStringToLong("2022-08-21"),
//                                    3,
//                                    3,
//                                    service.findByCategoryId(5),
//                                    5));
//
//            service.saveFood(new Food(
//                    48,
//                    "Gà Luộc",
//                    "v1636252457/hanoi_food_bank_project/uploaded_food/Meat/ga_luoc_1.jpg",
//                    "v1636252457/hanoi_food_bank_project/uploaded_food/Meat/ga_luoc_1.jpg,v1636252492/hanoi_food_bank_project/uploaded_food/Meat/ga_luoc_2.jpg",
//                    "Gà nhà nuôi mới thịt",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-24"),
//                            Utilities.convertStringToLong("2022-08-22"),
//                                    Utilities.convertStringToLong("2022-08-22"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(5),
//                                            5));
//
//            service.saveFood(new Food(
//                    49,
//                    "Ba Chỉ Lợn",
//                    "v1636253138/hanoi_food_bank_project/uploaded_food/Meat/ba_chi_lon_1.jpg",
//                    "v1636253138/hanoi_food_bank_project/uploaded_food/Meat/ba_chi_lon_1.jpg,v1636253161/hanoi_food_bank_project/uploaded_food/Meat/ba_chi_lon_2.jpg",
//                    "Ba chỉ lợn tươi đây",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-28"),
//                            Utilities.convertStringToLong("2022-08-23"),
//                                    Utilities.convertStringToLong("2022-08-23"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(5),
//                                            5));
//
//            service.saveFood(new Food(
//                    50,
//                    "Thịt Lợn Nạc Vai",
//                    "v1636253494/hanoi_food_bank_project/uploaded_food/Meat/thit_lon_nac_vai_1.jpg",
//                    "v1636253494/hanoi_food_bank_project/uploaded_food/Meat/thit_lon_nac_vai_1.jpg,v1636253515/hanoi_food_bank_project/uploaded_food/Meat/thit_lon_nac_vai_2.jpg ,v1636253537/hanoi_food_bank_project/uploaded_food/Meat/thit_lon_nac_vai_3.jpg ,v1636253564/hanoi_food_bank_project/uploaded_food/Meat/thit_lon_nac_vai_4.jpg",
//                    "Thịt lợn nhà nuôi",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-29"),
//                            Utilities.convertStringToLong("2022-08-24"),
//                                    Utilities.convertStringToLong("2022-08-24"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(5),
//                                            5));
//
//            service.saveFood(new Food(
//                    51,
//                    "Tôm Biển",
//                    "v1636253755/hanoi_food_bank_project/uploaded_food/Seafood/tom_bien_1.jpg",
//                    "v1636253755/hanoi_food_bank_project/uploaded_food/Seafood/tom_bien_1.jpg,v1636253758/hanoi_food_bank_project/uploaded_food/Seafood/tom_bien_2.jpg",
//                    "Tôm biển mới đánh sáng sớm",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-27"),
//                            Utilities.convertStringToLong("2022-08-25"),
//                                    Utilities.convertStringToLong("2022-08-25"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(6),
//                                            6));
//
//            service.saveFood(new Food(
//                    52,
//                    "Cua Hoàng Đế Hấp",
//                    "v1636253889/hanoi_food_bank_project/uploaded_food/Seafood/cua_hoang_de_hap_1.jpg",
//                    "v1636253889/hanoi_food_bank_project/uploaded_food/Seafood/cua_hoang_de_hap_1.jpg,v1636253880/hanoi_food_bank_project/uploaded_food/Seafood/cua_hoang_de_hap_2.jpg",
//                    "Cua Hoàng Đế Hấp hiếm có đây",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-27"),
//                            Utilities.convertStringToLong("2022-08-26"),
//                                    Utilities.convertStringToLong("2022-08-26"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(6),
//                                            6));
//
//            service.saveFood(new Food(
//                    53,
//                    "Ghẹ Rang Me",
//                    "v1636254043/hanoi_food_bank_project/uploaded_food/Seafood/ghe_rang_me_1.jpg",
//                    "v1636254043/hanoi_food_bank_project/uploaded_food/Seafood/ghe_rang_me_1.jpg,v1636254055/hanoi_food_bank_project/uploaded_food/Seafood/ghe_rang_me_2.jpg",
//                    "Ghe nguyên con rang me",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-28"),
//                            Utilities.convertStringToLong("2022-08-27"),
//                                    Utilities.convertStringToLong("2022-08-27"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(6),
//                                            6));
//
//            service.saveFood(new Food(
//                    54,
//                    "Ngao Hấp Xả",
//                    "v1636254264/hanoi_food_bank_project/uploaded_food/Seafood/ngao_hap_xa_1.jpg",
//                    "v1636254264/hanoi_food_bank_project/uploaded_food/Seafood/ngao_hap_xa_1.jpg,v1636254272/hanoi_food_bank_project/uploaded_food/Seafood/ngao_hap_xa_2.jpg ,v1636254281/hanoi_food_bank_project/uploaded_food/Seafood/ngao_hap_xa_3.jpg",
//                    "Ngao hấp xả ớt",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-29"),
//                            Utilities.convertStringToLong("2022-08-28"),
//                                    Utilities.convertStringToLong("2022-08-28"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(6),
//                                            6));
//
//            service.saveFood(new Food(
//                    55,
//                    "Hàu Nướng Phô Mai",
//                    "v1636254396/hanoi_food_bank_project/uploaded_food/Seafood/hau_nuong_pho_mai_1.jpg",
//                    "v1636254396/hanoi_food_bank_project/uploaded_food/Seafood/hau_nuong_pho_mai_1.jpg,v1636254407/hanoi_food_bank_project/uploaded_food/Seafood/hau_nuong_pho_mai_2.jpg",
//                    "Hàu nướng phô mai ngon hết ý",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-30"),
//                            Utilities.convertStringToLong("2022-08-29"),
//                                    Utilities.convertStringToLong("2022-08-29"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(6),
//                                            6));
//
//            service.saveFood(new Food(
//                    56,
//                    "Hàu Nướng Mỡ Hành",
//                    "v1636254585/hanoi_food_bank_project/uploaded_food/Seafood/hau_nuong_mo_hanh_1.jpg",
//                    "v1636254585/hanoi_food_bank_project/uploaded_food/Seafood/hau_nuong_mo_hanh_1.jpg,v1636254585/hanoi_food_bank_project/uploaded_food/Seafood/hau_nuong_mo_hanh_2.jpg",
//                    "Hàu nướng mỡ hành ngon hết ý",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-31"),
//                            Utilities.convertStringToLong("2022-08-30"),
//                                    Utilities.convertStringToLong("2022-08-30"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(6),
//                                            6));
//
//            service.saveFood(new Food(
//                    57,
//                    "Mực Hấp",
//                    "v1636254712/hanoi_food_bank_project/uploaded_food/Seafood/muc_hap_1.jpg",
//                    "v1636254712/hanoi_food_bank_project/uploaded_food/Seafood/muc_hap_1.jpg,v1636254712/hanoi_food_bank_project/uploaded_food/Seafood/muc_hap_2.jpg",
//                    "Mực tươi hấp",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-11-01"),
//                    Utilities.convertStringToLong("2022-08-31"),
//                    Utilities.convertStringToLong("2022-08-31"),
//                    3,
//                    3,
//                    service.findByCategoryId(6),
//                    6));
//
//            service.saveFood(new Food(
//                    58,
//                    "Cá Hồi Nauy",
//                    "v1636254876/hanoi_food_bank_project/uploaded_food/Seafood/ca_hoi_na_uy_1.jpg",
//                    "v1636254876/hanoi_food_bank_project/uploaded_food/Seafood/ca_hoi_na_uy_1.jpg,v1636254876/hanoi_food_bank_project/uploaded_food/Seafood/ca_hoi_na_uy_2.jpg",
//                    "Cá hồi Nauy nhập khẩu",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-16"),
//                    Utilities.convertStringToLong("2022-08-15"),
//                    Utilities.convertStringToLong("2022-08-15"),
//                    3,
//                    3,
//                    service.findByCategoryId(6),
//                    6));
//
//            service.saveFood(new Food(
//                    59,
//                    "Bề Bề Hấp",
//                    "v1636254917/hanoi_food_bank_project/uploaded_food/Seafood/be_be_hap_1.jpg",
//                    "v1636254917/hanoi_food_bank_project/uploaded_food/Seafood/be_be_hap_1.jpg,v1636254917/hanoi_food_bank_project/uploaded_food/Seafood/be_be_hap_2.jpg",
//                    "Bề bề to,chắc",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-17"),
//                    Utilities.convertStringToLong("2022-08-16"),
//                    Utilities.convertStringToLong("2022-08-16"),
//                    3,
//                    3,
//                    service.findByCategoryId(6),
//                    6));
//
//            service.saveFood(new Food(
//                    60,
//                    "Ốc Hương Xào Bơ Tỏi",
//                    "v1636255152/hanoi_food_bank_project/uploaded_food/Seafood/oc_huong_xao_bo_toi_1.jpg",
//                    "v1636255152/hanoi_food_bank_project/uploaded_food/Seafood/oc_huong_xao_bo_toi_1.jpg,v1636255152/hanoi_food_bank_project/uploaded_food/Seafood/oc_huong_xao_bo_toi_2.jpg",
//                    "Ốc ngon, thơm",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-18"),
//                    Utilities.convertStringToLong("2022-08-17"),
//                            Utilities.convertStringToLong("2022-08-17"),
//                                    3,
//                                    3,
//                                    service.findByCategoryId(6),
//                                    6));
//
//            service.saveFood(new Food(
//                    61,
//                    "Rau Muống",
//                    "v1636255284/hanoi_food_bank_project/uploaded_food/Vegetables/rau_muong_1.jpg",
//                    "v1636255284/hanoi_food_bank_project/uploaded_food/Vegetables/rau_muong_1.jpg,v1636255284/hanoi_food_bank_project/uploaded_food/Vegetables/rau_muong_2.jpg",
//                    "Rau củ nhà trồng",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-22"),
//                            Utilities.convertStringToLong("2022-08-18"),
//                                    Utilities.convertStringToLong("2022-08-18"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(7),
//                                            7));
//
//            service.saveFood(new Food(
//                    62,
//                    "Rau Cải Ngọt",
//                    "v1636255347/hanoi_food_bank_project/uploaded_food/Vegetables/rau_cai_ngot_1.jpg",
//                    "v1636255347/hanoi_food_bank_project/uploaded_food/Vegetables/rau_cai_ngot_1.jpg; v1636255347/hanoi_food_bank_project/uploaded_food/Vegetables/rau_cai_ngot_2.jpg",
//                    "Rau củ nhà trồng",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-23"),
//                            Utilities.convertStringToLong("2022-08-19"),
//                                    Utilities.convertStringToLong("2022-08-19"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(7),
//                                            7));
//
//            service.saveFood(new Food(
//                    63,
//                    "Bắp Cải",
//                    "v1636256397/hanoi_food_bank_project/uploaded_food/Vegetables/bap_cai_1.jpg",
//                    "v1636256397/hanoi_food_bank_project/uploaded_food/Vegetables/bap_cai_1.jpg,v1636256397/hanoi_food_bank_project/uploaded_food/Vegetables/bap_cai_2.jpg",
//                    "Rau củ nhà trồng",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-24"),
//                    Utilities.convertStringToLong("2022-08-20"),
//                            Utilities.convertStringToLong("2022-08-20"),
//                                    3,
//                                    3,
//                                    service.findByCategoryId(7),
//                                    7));
//
//            service.saveFood(new Food(
//                    64,
//                    "Cà Rốt",
//                    "v1636256506/hanoi_food_bank_project/uploaded_food/Vegetables/ca_rot_1.jpg",
//                    "v1636256506/hanoi_food_bank_project/uploaded_food/Vegetables/ca_rot_1.jpg,v1636256506/hanoi_food_bank_project/uploaded_food/Vegetables/ca_rot_2.jpg",
//                    "Rau củ nhà trồng",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-25"),
//                            Utilities.convertStringToLong("2022-08-21"),
//                                    Utilities.convertStringToLong("2022-08-21"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(7),
//                                            7));
//
//            service.saveFood(new Food(
//                    65,
//                    "Xu Hào",
//                    "v1636256605/hanoi_food_bank_project/uploaded_food/Vegetables/xu_hao_1.jpg",
//                    "v1636256605/hanoi_food_bank_project/uploaded_food/Vegetables/xu_hao_1.jpg,v1636256605/hanoi_food_bank_project/uploaded_food/Vegetables/xu_hao_2.jpg",
//                    "Rau củ nhà trồng",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-26"),
//                    Utilities.convertStringToLong("2022-08-22"),
//                            Utilities.convertStringToLong("2022-08-22"),
//                                    3,
//                                    3,
//                                    service.findByCategoryId(7),
//                                    7));
//
//            service.saveFood(new Food(
//                    66,
//                    "Rau Ngót",
//                    "v1636257205/hanoi_food_bank_project/uploaded_food/Vegetables/rau_ngot_1.jpg",
//                    "v1636257205/hanoi_food_bank_project/uploaded_food/Vegetables/rau_ngot_1.jpg,v1636257205/hanoi_food_bank_project/uploaded_food/Vegetables/rau_ngot_2.jpg",
//                    "Rau củ nhà trồng",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-27"),
//                            Utilities.convertStringToLong("2022-08-23"),
//                                    Utilities.convertStringToLong("2022-08-23"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(7),
//                                            7));
//
//            service.saveFood(new Food(
//                    67,
//                    "Rau Mồng Tơi",
//                    "v1636258358/hanoi_food_bank_project/uploaded_food/Vegetables/rau_mong_toi_1.jpg",
//                    "v1636258358/hanoi_food_bank_project/uploaded_food/Vegetables/rau_mong_toi_1.jpg,v1636258358/hanoi_food_bank_project/uploaded_food/Vegetables/rau_mong_toi_1.jpg",
//                    "Rau củ nhà trồng",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-28"),
//                            Utilities.convertStringToLong("2022-08-24"),
//                                    Utilities.convertStringToLong("2022-08-24"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(7),
//                                            7));
//
//            service.saveFood(new Food(
//                    68,
//                    "Khoai Tây",
//                    "v1636258430/hanoi_food_bank_project/uploaded_food/Vegetables/khoai_tay_1.jpg",
//                    "v1636258430/hanoi_food_bank_project/uploaded_food/Vegetables/khoai_tay_1.jpg,v1636258430/hanoi_food_bank_project/uploaded_food/Vegetables/khoai_tay_2.jpg",
//                    "Rau củ nhà trồng",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-29"),
//                            Utilities.convertStringToLong("2022-08-25"),
//                                    Utilities.convertStringToLong("2022-08-25"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(7),
//                                            7));
//
//            service.saveFood(new Food(
//                    69,
//                    "Khoai Lang",
//                    "v1636258485/hanoi_food_bank_project/uploaded_food/Vegetables/khoai_lang_1.jpg",
//                    "v1636258485/hanoi_food_bank_project/uploaded_food/Vegetables/khoai_lang_1.jpg,v1636258485/hanoi_food_bank_project/uploaded_food/Vegetables/khoai_lang_2.jpg",
//                    "Rau củ nhà trồng",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-30"),
//                            Utilities.convertStringToLong("2022-08-26"),
//                                    Utilities.convertStringToLong("2022-08-26"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(7),
//                                            7));
//
//            service.saveFood(new Food(
//                    70,
//                    "Xu Xu",
//                    "v1636258530/hanoi_food_bank_project/uploaded_food/Vegetables/xu_xu_1.jpg",
//                    "v1636258530/hanoi_food_bank_project/uploaded_food/Vegetables/xu_xu_1.jpg,v1636258530/hanoi_food_bank_project/uploaded_food/Vegetables/xu_xu_2.jpg",
//                    "Rau củ nhà trồng",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-31"),
//                            Utilities.convertStringToLong("2022-08-17"),
//                                    Utilities.convertStringToLong("2022-08-27"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(7),
//                                            7));
//
//            service.saveFood(new Food(
//                    71,
//                    "Mướp Xào Nấm ",
//                    "v1636258804/hanoi_food_bank_project/uploaded_food/Vegetarian Food/muop_xao_nam_chay.jpg",
//                    "v1636258804/hanoi_food_bank_project/uploaded_food/Vegetarian Food/muop_xao_nam_chay.jpg",
//                    "Làm cho người ăn chay",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-29"),
//                            Utilities.convertStringToLong("2022-08-28"),
//                                    Utilities.convertStringToLong("2022-08-28"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(8),
//                                            8));
//
//            service.saveFood(new Food(
//                    72,
//                    "Đậu Bắp Xào Sa Tế",
//                    "v1636258977/hanoi_food_bank_project/uploaded_food/Vegetarian Food/dau_bap_xao_sa_te.jpg",
//                    "v1636258977/hanoi_food_bank_project/uploaded_food/Vegetarian Food/dau_bap_xao_sa_te.jpg",
//                    "Làm cho người ăn chay",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-30"),
//                            Utilities.convertStringToLong("2022-08-29"),
//                                    Utilities.convertStringToLong("2022-08-29"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(8),
//                                            8));
//
//            service.saveFood(new Food(
//                    73,
//                    "Nấm Rơm Kho Chao",
//                    "v1636259005/hanoi_food_bank_project/uploaded_food/Vegetarian Food/nam_rom_kho_chao.jpg",
//                    "v1636259005/hanoi_food_bank_project/uploaded_food/Vegetarian Food/nam_rom_kho_chao.jpg",
//                    "Làm cho người ăn chay",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-31"),
//                            Utilities.convertStringToLong("2022-08-30"),
//                                    Utilities.convertStringToLong("2022-08-30"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(8),
//                                            8));
//
//            service.saveFood(new Food(
//                    74,
//                    "Bông Cải Xào Tiêu Xanh",
//                    "v1636258946/hanoi_food_bank_project/uploaded_food/Vegetarian Food/bong_cai_xao_tieu_xanh.jpg",
//                    "v1636258946/hanoi_food_bank_project/uploaded_food/Vegetarian Food/bong_cai_xao_tieu_xanh.jpg",
//                    "Làm cho người ăn chay",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-11-01"),
//                            Utilities.convertStringToLong("2022-08-31"),
//                                    Utilities.convertStringToLong("2022-08-31"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(8),
//                                            8));
//
//            service.saveFood(new Food(
//                    75,
//                    "Cà Tím Áp Chảo",
//                    "v1636259056/hanoi_food_bank_project/uploaded_food/Vegetarian Food/ca_tim_ap_chao.jpg",
//                    "v1636259056/hanoi_food_bank_project/uploaded_food/Vegetarian Food/ca_tim_ap_chao.jpg",
//                    "Làm cho người ăn chay",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-16"),
//                    Utilities.convertStringToLong("2022-08-15"),
//                    Utilities.convertStringToLong("2022-08-15"),
//                    3,
//                    3,
//                    service.findByCategoryId(8),
//                    8));
//
//            service.saveFood(new Food(
//                    76,
//                    "Canh Nấm Tàu Hũ",
//                    "v1636259086/hanoi_food_bank_project/uploaded_food/Vegetarian Food/canh_nam_tau_hu.jpg",
//                    "v1636259086/hanoi_food_bank_project/uploaded_food/Vegetarian Food/canh_nam_tau_hu.jpg",
//                    "Làm cho người ăn chay",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-17"),
//                            Utilities.convertStringToLong("2022-08-16"),
//                                    Utilities.convertStringToLong("2022-08-16"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(8),
//                                            8));
//
//            service.saveFood(new Food(
//                    77,
//                    "Cơm Chiên Bí Đỏ",
//                    "v1636259236/hanoi_food_bank_project/uploaded_food/Vegetarian Food/com_chien_bi_do.jpg",
//                    "v1636259236/hanoi_food_bank_project/uploaded_food/Vegetarian Food/com_chien_bi_do.jpg",
//                    "Làm cho người ăn chay",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-18"),
//                            Utilities.convertStringToLong("2022-08-17"),
//                                    Utilities.convertStringToLong("2022-08-17"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(8),
//                                            8));
//
//            service.saveFood(new Food(
//                    78,
//                    "Đậu Hũ Nhồi Nấm Sốt Cam",
//                    "v1636259266/hanoi_food_bank_project/uploaded_food/Vegetarian Food/dau_hu_nhoi_nam_xot_cam.jpg",
//                    "v1636259266/hanoi_food_bank_project/uploaded_food/Vegetarian Food/dau_hu_nhoi_nam_xot_cam.jpg",
//                    "Làm cho người ăn chayy",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-19"),
//                            Utilities.convertStringToLong("2022-08-18"),
//                                    Utilities.convertStringToLong("2022-08-18"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(8),
//                                            8));
//
//            service.saveFood(new Food(
//                    79,
//                    "Cuốn Diếp",
//                    "v1636259299/hanoi_food_bank_project/uploaded_food/Vegetarian Food/cuon_diep.jpg",
//                    "v1636259299/hanoi_food_bank_project/uploaded_food/Vegetarian Food/cuon_diep.jpg",
//                    "Làm cho người ăn chay",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-20"),
//                            Utilities.convertStringToLong("2022-08-19"),
//                                    Utilities.convertStringToLong("2022-08-19"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(8),
//                                            8));
//
//            service.saveFood(new Food(
//                    80,
//                    "Cơm Tấm Bì Chay",
//                    "v1636259029/hanoi_food_bank_project/uploaded_food/Vegetarian Food/com_tam_bi_chay.jpg",
//                    "v1636259029/hanoi_food_bank_project/uploaded_food/Vegetarian Food/com_tam_bi_chay.jpg",
//                    "Làm cho người ăn chay",
//                    "Đỉnh của chóp!",
//                    Utilities.convertStringToLong("2022-08-21"),
//                            Utilities.convertStringToLong("2022-08-20"),
//                                    Utilities.convertStringToLong("2022-08-20"),
//                                            3,
//                                            3,
//                                            service.findByCategoryId(8),
//                                            8));
//
//
//            service.saveFood(new Food(
//                    81,
//                    "Xoai khong hat",
//                    "v1636225794/hanoi_food_bank_project/uploaded_food/Fruit/xoai3_dwlb0l.jpg",
//                    "v1636225794/hanoi_food_bank_project/uploaded_food/Fruit/xoai3_dwlb0l.jpg,v1636225794/hanoi_food_bank_project/uploaded_food/Fruit/xoai2_qgiqke.png,v1636225793/hanoi_food_bank_project/uploaded_food/Fruit/xoai1_yqedst.jpg,",
//                    "Xoai khong hat that ngon",
//                    "Xoai khong hat dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    5,
//                    5,
//                    service.findByCategoryId(9),
//                    9
//            ));
//
//            service.saveFood(new Food(
//                    82,
//                    "Thanh long",
//                    "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/thanhlong1_wh9zfe.jpg",
//                    "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/thanhlong1_wh9zfe.jpg,v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/thanhlong3_kdweka.jpg,v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/thanhlong2_supgf3.jpg,",
//                    "Thanh long that ngon",
//                    "Thanh long dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-05"),
//                    Utilities.convertStringToLong("2022-11-02"),
//                    Utilities.convertStringToLong("2022-11-02"),
//                    5,
//                    5,
//                    service.findByCategoryId(9),
//                    9
//            ));
//            service.saveFood(new Food(
//                    83,
//                    "Roi",
//                    "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/roi3_qu2vv8.png",
//                    "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/roi3_qu2vv8.png,v1636225791/hanoi_food_bank_project/uploaded_food/Fruit/roi1_d7yqc7.jpg,v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/roi2_omk3sb.webp,",
//                    "Roi that ngon",
//                    "Roi dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-06"),
//                    Utilities.convertStringToLong("2022-11-03"),
//                    Utilities.convertStringToLong("2022-11-03"),
//                    5,
//                    5,
//                    service.findByCategoryId(9),
//                    9
//            ));
//            service.saveFood(new Food(
//                    84,
//                    "Tao",
//                    "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/tao3_ghdoug.jpg",
//                    "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/tao3_ghdoug.jpg,v1636225791/hanoi_food_bank_project/uploaded_food/Fruit/tao1_orkolx.jpg,v1636225791/hanoi_food_bank_project/uploaded_food/Fruit/tao2_oilqm4.jpg,",
//                    "Tao that ngon",
//                    "Tao dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-08"),
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-04"),
//                    5,
//                    5,
//                    service.findByCategoryId(9),
//                    9
//            ));
//            service.saveFood(new Food(
//                    85,
//                    "Mit",
//                    "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/mit3_frlbub.jpg",
//                    "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/mit3_frlbub.jpg,v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/mit1_ceylcu.jpg,v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/mit2_farjxu.jpg,",
//                    "Mit that ngon",
//                    "Mit dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-09"),
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-04"),
//                    5,
//                    5,
//                    service.findByCategoryId(9),
//                    9
//            ));
//            service.saveFood(new Food(
//                    86,
//                    "Chom chom",
//                    "v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/chomchom2_updkwz.jpg",
//                    "v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/chomchom2_updkwz.jpg,v1636225789/hanoi_food_bank_project/uploaded_food/Fruit/chomchom1_mvxlpy.jpg,v1636225788/hanoi_food_bank_project/uploaded_food/Fruit/chomchom3_npr4bf.jpg,",
//                    "Chom chom that ngon",
//                    "Chom chom dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-10"),
//                    Utilities.convertStringToLong("2022-11-05"),
//                    Utilities.convertStringToLong("2022-11-05"),
//                    5,
//                    5,
//                    service.findByCategoryId(9),
//                    9
//            ));
//            service.saveFood(new Food(
//                    87,
//                    "Khe",
//                    "v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/khe2_tkcxpz.jpg",
//                    "v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/khe2_tkcxpz.jpg,v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/khe3_qipwz4.jpg,v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/khe1_qng4c1.jpg,",
//                    "Khe that ngon",
//                    "Khe dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-09"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    5,
//                    5,
//                    service.findByCategoryId(9),
//                    9
//            ));
//            service.saveFood(new Food(
//                    88,
//                    "Chanh day",
//                    "v1636225789/hanoi_food_bank_project/uploaded_food/Fruit/chanhday1_neq9fr.jpg",
//                    "v1636225789/hanoi_food_bank_project/uploaded_food/Fruit/chanhday1_neq9fr.jpg,v1636225788/hanoi_food_bank_project/uploaded_food/Fruit/chanhday2_ckgnpu.jpg,v1636225787/hanoi_food_bank_project/uploaded_food/Fruit/chanhday3_h1tcov.jpg,",
//                    "Chanh day that ngon",
//                    "Chanh day dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-10"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    5,
//                    5,
//                    service.findByCategoryId(9),
//                    9
//            ));
//            service.saveFood(new Food(
//                    89,
//                    "Dau tay",
//                    "v1636225789/hanoi_food_bank_project/uploaded_food/Fruit/dautay3_xvfgbz.jpg",
//                    "v1636225789/hanoi_food_bank_project/uploaded_food/Fruit/dautay3_xvfgbz.jpg,v1636225789/hanoi_food_bank_project/uploaded_food/Fruit/dautay1_mu2a9q.jpg,v1636225789/hanoi_food_bank_project/uploaded_food/Fruit/dautay2_nh7w41.jpg,",
//                    "Dau tay that ngon",
//                    "Dau tay dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-10"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    5,
//                    5,
//                    service.findByCategoryId(9),
//                    9
//            ));
//            service.saveFood(new Food(
//                    90,
//                    "Chuoi",
//                    "v1636225788/hanoi_food_bank_project/uploaded_food/Fruit/banana3_aj8e6i.jpg",
//                    "v1636225788/hanoi_food_bank_project/uploaded_food/Fruit/banana3_aj8e6i.jpg,v1636225787/hanoi_food_bank_project/uploaded_food/Fruit/banana1_lzk7kx.jpg,v1636225787/hanoi_food_bank_project/uploaded_food/Fruit/banana2_r7x04b.jpg,",
//                    "Chuoi that ngon",
//                    "Chuoi dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-09"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    5,
//                    5,
//                    service.findByCategoryId(9),
//                    9
//            ));
//
//
//            service.saveFood(new Food(
//                    91,
//                    "Pizza",
//                    "v1636225761/hanoi_food_bank_project/uploaded_food/Fast%20Food/pizza2_zd7dtl.png",
//                    "v1636225761/hanoi_food_bank_project/uploaded_food/Fast%20Food/pizza2_zd7dtl.png,v1636225760/hanoi_food_bank_project/uploaded_food/Fast%20Food/pizza3_bpu8lw.jpg,v1636225757/hanoi_food_bank_project/uploaded_food/Fast%20Food/pizza1_rw6vlp.jpg,",
//                    "Pizza that ngon",
//                    "Pizza dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    5,
//                    5,
//                    service.findByCategoryId(10),
//                    10
//            ));
//            service.saveFood(new Food(
//                    92,
//                    "Xuc xich",
//                    "v1636225761/hanoi_food_bank_project/uploaded_food/Fast%20Food/xucxich2_ldcmlf.jpg",
//                    "v1636225761/hanoi_food_bank_project/uploaded_food/Fast%20Food/xucxich2_ldcmlf.jpg,v1636225760/hanoi_food_bank_project/uploaded_food/Fast%20Food/xucxich3_o6ggpj.jpg,v1636225759/hanoi_food_bank_project/uploaded_food/Fast%20Food/xucxich1_evvtpx.jpg,",
//                    "Xuc xich that ngon",
//                    "Xuc xich dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-05"),
//                    Utilities.convertStringToLong("2022-11-02"),
//                    Utilities.convertStringToLong("2022-11-02"),
//                    5,
//                    5,
//                    service.findByCategoryId(10),
//                    10
//            ));
//            service.saveFood(new Food(
//                    93,
//                    "Khoai tay lac",
//                    "v1636225759/hanoi_food_bank_project/uploaded_food/Fast%20Food/khoaitaylac3_ckgctu.png",
//                    "v1636225759/hanoi_food_bank_project/uploaded_food/Fast%20Food/khoaitaylac3_ckgctu.png,v1636225756/hanoi_food_bank_project/uploaded_food/Fast%20Food/khoaitaylac2_i82ocx.jpg,v1636225756/hanoi_food_bank_project/uploaded_food/Fast%20Food/khoaitaylac1_fqx5kb.jpg,",
//                    "Khoai tay lac that ngon",
//                    "Khoai tay lac dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-06"),
//                    Utilities.convertStringToLong("2022-11-03"),
//                    Utilities.convertStringToLong("2022-11-03"),
//                    5,
//                    5,
//                    service.findByCategoryId(10),
//                    10
//            ));
//            service.saveFood(new Food(
//                    94,
//                    "Xien thit nuong",
//                    "v1636225759/hanoi_food_bank_project/uploaded_food/Fast%20Food/xienthitnuong1_aoombt.jpg",
//                    "v1636225759/hanoi_food_bank_project/uploaded_food/Fast%20Food/xienthitnuong1_aoombt.jpg,v1636225758/hanoi_food_bank_project/uploaded_food/Fast%20Food/xienthitnuong3_bwmbfr.jpg,v1636225758/hanoi_food_bank_project/uploaded_food/Fast%20Food/xienthitnuong2_qdemco.jpg,",
//                    "Xien thit nuong that ngon",
//                    "Xien thit nuong dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-08"),
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-04"),
//                    5,
//                    5,
//                    service.findByCategoryId(10),
//                    10
//            ));
//            service.saveFood(new Food(
//                    95,
//                    "Sandwich",
//                    "v1636225758/hanoi_food_bank_project/uploaded_food/Fast%20Food/sandwich3_veett9.jpg",
//                    "v1636225758/hanoi_food_bank_project/uploaded_food/Fast%20Food/sandwich3_veett9.jpg,v1636225757/hanoi_food_bank_project/uploaded_food/Fast%20Food/sandwich2_fud8vm.jpg,v1636225757/hanoi_food_bank_project/uploaded_food/Fast%20Food/sandwich1_og0rbl.jpg,",
//                    "Sandwich that ngon",
//                    "Sandwich dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-08"),
//                    Utilities.convertStringToLong("2022-11-05"),
//                    Utilities.convertStringToLong("2022-11-05"),
//                    5,
//                    5,
//                    service.findByCategoryId(10),
//                    10
//            ));
//            service.saveFood(new Food(
//                    96,
//                    "Nem chua nuong",
//                    "v1636225757/hanoi_food_bank_project/uploaded_food/Fast%20Food/nemchuanuong2_hlvasd.jpg",
//                    "v1636225757/hanoi_food_bank_project/uploaded_food/Fast%20Food/nemchuanuong2_hlvasd.jpg,v1636225757/hanoi_food_bank_project/uploaded_food/Fast%20Food/nemchuanuong3_lvtpsc.jpg,v1636225756/hanoi_food_bank_project/uploaded_food/Fast%20Food/nemchuanuong1_aomlvx.jpg,",
//                    "Nem chua nuong that ngon",
//                    "Nem chua nuong dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-09"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    5,
//                    5,
//                    service.findByCategoryId(10),
//                    10
//            ));
//            service.saveFood(new Food(
//                    97,
//                    "Ga ran",
//                    "v1636225756/hanoi_food_bank_project/uploaded_food/Fast%20Food/garan2_yoaeq2.jpg",
//                    "v1636225756/hanoi_food_bank_project/uploaded_food/Fast%20Food/garan2_yoaeq2.jpg,v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/garan3_s6ukhk.jpg,v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/garan1_ragr0c.jpg,",
//                    "Ga ran that ngon",
//                    "Ga ran dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-03"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    5,
//                    5,
//                    service.findByCategoryId(10),
//                    10
//            ));
//            service.saveFood(new Food(
//                    98,
//                    "Hamburger",
//                    "v1636225756/hanoi_food_bank_project/uploaded_food/Fast%20Food/hamburger3_nqmtvf.jpg",
//                    "v1636225756/hanoi_food_bank_project/uploaded_food/Fast%20Food/hamburger3_nqmtvf.jpg,v1636225756/hanoi_food_bank_project/uploaded_food/Fast%20Food/hamburger2_hz8vj7.jpg,v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/hamburger1_yrgt9m.jpg,",
//                    "Hamburger that ngon",
//                    "Hamburger dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-09"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    5,
//                    5,
//                    service.findByCategoryId(10),
//                    10
//            ));
//            service.saveFood(new Food(
//                    99,
//                    "Ca vien chien",
//                    "v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/cavienchien1_cf2pgn.png",
//                    "v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/cavienchien1_cf2pgn.png,v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/cavienchien3_vl9avj.jpg,v1636225754/hanoi_food_bank_project/uploaded_food/Fast%20Food/cavienchien2_u7tlxr.jpg,",
//                    "Ca vien chien that ngon",
//                    "Ca vien chien dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    5,
//                    5,
//                    service.findByCategoryId(10),
//                    10
//            ));
//            service.saveFood(new Food(
//                    100,
//                    "Donuts",
//                    "v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/donuts1_vfwgsu.jpg",
//                    "v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/donuts1_vfwgsu.jpg,v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/donuts3_cn2bsr.jpg,v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/donuts2_jgxscp.jpg,",
//                    "Donuts that ngon",
//                    "Donuts dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    5,
//                    5,
//                    service.findByCategoryId(10),
//                    10
//            ));
//
//
//            service.saveFood(new Food(
//                    101,
//                    "Snack rong bien",
//                    "v1636225828/hanoi_food_bank_project/uploaded_food/Snacks/snackrongbien3_c0ysfm.png",
//                    "v1636225828/hanoi_food_bank_project/uploaded_food/Snacks/snackrongbien3_c0ysfm.png,v1636225827/hanoi_food_bank_project/uploaded_food/Snacks/snackrongbien2_snjfw2.jpg,v1636225825/hanoi_food_bank_project/uploaded_food/Snacks/snackrongbien1_od5tdp.jpg,",
//                    "Snack rong bien that ngon",
//                    "Snack rong bien dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-12"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    5,
//                    5,
//                    service.findByCategoryId(11),
//                    11
//            ));
//            service.saveFood(new Food(
//                    102,
//                    "Snack pho mai",
//                    "v1636225826/hanoi_food_bank_project/uploaded_food/Snacks/snackphomai2_qjglw7.jpg",
//                    "v1636225826/hanoi_food_bank_project/uploaded_food/Snacks/snackphomai2_qjglw7.jpg,v1636225826/hanoi_food_bank_project/uploaded_food/Snacks/snackphomai3_wvhmkn.jpg,v1636225825/hanoi_food_bank_project/uploaded_food/Snacks/snackphomai1_vdqftr.jpg,",
//                    "Snack pho mai that ngon",
//                    "Snack pho mai dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-11"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    5,
//                    5,
//                    service.findByCategoryId(11),
//                    11
//            ));
//            service.saveFood(new Food(
//                    103,
//                    "Snack muc",
//                    "v1636225826/hanoi_food_bank_project/uploaded_food/Snacks/snackmuc1_ayza92.jpg",
//                    "v1636225826/hanoi_food_bank_project/uploaded_food/Snacks/snackmuc1_ayza92.jpg,v1636225825/hanoi_food_bank_project/uploaded_food/Snacks/snackmuc3_g6auwz.jpg,v1636225824/hanoi_food_bank_project/uploaded_food/Snacks/snackmuc2_sofqn6.jpg,",
//                    "Snack muc that ngon",
//                    "Snack muc dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-11"),
//                    Utilities.convertStringToLong("2022-11-05"),
//                    Utilities.convertStringToLong("2022-11-05"),
//                    5,
//                    5,
//                    service.findByCategoryId(11),
//                    11
//            ));
//            service.saveFood(new Food(
//                    104,
//                    "Snack khoai tay",
//                    "v1636225825/hanoi_food_bank_project/uploaded_food/Snacks/snackkhoaitay2_wwbsde.jpg",
//                    "v1636225825/hanoi_food_bank_project/uploaded_food/Snacks/snackkhoaitay2_wwbsde.jpg,v1636225824/hanoi_food_bank_project/uploaded_food/Snacks/snackkhoaitay3_am3abw.jpg,v1636225824/hanoi_food_bank_project/uploaded_food/Snacks/snackkhoaitay1_ddkxtn.jpg,",
//                    "Snack khoai tay that ngon",
//                    "Snack khoai tay dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-10"),
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-04"),
//                    5,
//                    5,
//                    service.findByCategoryId(11),
//                    11
//            ));
//            service.saveFood(new Food(
//                    105,
//                    "Snack hanh",
//                    "v1636225824/hanoi_food_bank_project/uploaded_food/Snacks/snackhanh3_f7f3mf.jpg",
//                    "v1636225824/hanoi_food_bank_project/uploaded_food/Snacks/snackhanh3_f7f3mf.jpg,v1636225824/hanoi_food_bank_project/uploaded_food/Snacks/snackhanh1_sfnebc.jpg,v1636225823/hanoi_food_bank_project/uploaded_food/Snacks/snackhanh2_h4nm5m.jpg,",
//                    "Snack hanh that ngon",
//                    "Snack hanh dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-09"),
//                    Utilities.convertStringToLong("2022-11-03"),
//                    Utilities.convertStringToLong("2022-11-03"),
//                    5,
//                    5,
//                    service.findByCategoryId(11),
//                    11
//            ));
//            service.saveFood(new Food(
//                    106,
//                    "Snack cua",
//                    "v1636225824/hanoi_food_bank_project/uploaded_food/Snacks/snackcua3_yfzv5r.jpg",
//                    "v1636225824/hanoi_food_bank_project/uploaded_food/Snacks/snackcua3_yfzv5r.jpg,v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/snackcua1_zqkspt.jpg,v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/snackcua2_qmdza3.jpg,",
//                    "Snack cua that ngon",
//                    "Snack cua dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-08"),
//                    Utilities.convertStringToLong("2022-11-02"),
//                    Utilities.convertStringToLong("2022-11-02"),
//                    5,
//                    5,
//                    service.findByCategoryId(11),
//                    11
//            ));
//            service.saveFood(new Food(
//                    107,
//                    "Snack bi do",
//                    "v1636225823/hanoi_food_bank_project/uploaded_food/Snacks/snackbido2_eh2tnw.jpg",
//                    "v1636225823/hanoi_food_bank_project/uploaded_food/Snacks/snackbido2_eh2tnw.jpg,v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/snackbido3_tjchfl.jpg,v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/snackbido1_hc72a4.jpg,",
//                    "Snack bi do that ngon",
//                    "Snack bi do dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-07"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    5,
//                    5,
//                    service.findByCategoryId(11),
//                    11
//            ));
//            service.saveFood(new Food(
//                    108,
//                    "Phong tom",
//                    "v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/phongtom2_kocvjd.jpg",
//                    "v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/phongtom2_kocvjd.jpg,v1636225821/hanoi_food_bank_project/uploaded_food/Snacks/phongtom1_wpinup.jpg,v1636225821/hanoi_food_bank_project/uploaded_food/Snacks/phongtom3_l4w5ko.jpg,",
//                    "Phong tom that ngon",
//                    "Phong tom dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-10"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    5,
//                    5,
//                    service.findByCategoryId(11),
//                    11
//            ));
//            service.saveFood(new Food(
//                    109,
//                    "Khoai tay que",
//                    "v1636225821/hanoi_food_bank_project/uploaded_food/Snacks/khoaitayque1_jywhvn.jpg",
//                    "v1636225821/hanoi_food_bank_project/uploaded_food/Snacks/khoaitayque1_jywhvn.jpg,v1636225821/hanoi_food_bank_project/uploaded_food/Snacks/khoaitayque2_qsmupw.jpg,v1636225820/hanoi_food_bank_project/uploaded_food/Snacks/khoaitayque3_mgvr84.jpg,",
//                    "Khoai tay que that ngon",
//                    "Khoai tay que dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-08"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    5,
//                    5,
//                    service.findByCategoryId(11),
//                    11
//            ));
//            service.saveFood(new Food(
//                    110,
//                    "Snack bap ngot",
//                    "v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/snackbapngot3_a7qf9y.jpg",
//                    "v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/snackbapngot3_a7qf9y.jpg,v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/snackbapngot1_hmsikz.jpg,v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/snackbapngot2_eoybwj.jpg,",
//                    "Snack bap ngot that ngon",
//                    "Snack bap ngot dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-14"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    5,
//                    5,
//                    service.findByCategoryId(11),
//                    11
//            ));
//
//
//            service.saveFood(new Food(
//                    111,
//                    "Salad",
//                    "v1636225854/hanoi_food_bank_project/uploaded_food/Others/salat3_lw1tgv.jpg",
//                    "v1636225854/hanoi_food_bank_project/uploaded_food/Others/salat3_lw1tgv.jpg,v1636225851/hanoi_food_bank_project/uploaded_food/Others/salat1_gu5k9s.jpg,v1636225851/hanoi_food_bank_project/uploaded_food/Others/salat2_fekoqs.jpg,",
//                    "Salad that ngon",
//                    "Salad dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-02"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    Utilities.convertStringToLong("2022-11-01"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//            service.saveFood(new Food(
//                    112,
//                    "Tiet canh",
//                    "v1636225853/hanoi_food_bank_project/uploaded_food/Others/tietcanh1_ie4qky.png",
//                    "v1636225853/hanoi_food_bank_project/uploaded_food/Others/tietcanh1_ie4qky.png,v1636225854/hanoi_food_bank_project/uploaded_food/Others/tietcanh2_qapmhw.png,v1636225852/hanoi_food_bank_project/uploaded_food/Others/tietcanh3_skjdo9.jpg,",
//                    "Tiet canh that ngon",
//                    "Tiet canh dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-03"),
//                    Utilities.convertStringToLong("2022-11-02"),
//                    Utilities.convertStringToLong("2022-11-02"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//            service.saveFood(new Food(
//                    113,
//                    "Pho cuon",
//                    "v1636225852/hanoi_food_bank_project/uploaded_food/Others/phocuon2_myzqcv.png",
//                    "v1636225852/hanoi_food_bank_project/uploaded_food/Others/phocuon2_myzqcv.png,v1636225851/hanoi_food_bank_project/uploaded_food/Others/phocuon3_pehimo.jpg,v1636225850/hanoi_food_bank_project/uploaded_food/Others/phocuon1_wqyscl.jpg,",
//                    "Pho cuon that ngon",
//                    "Pho cuon dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-03"),
//                    Utilities.convertStringToLong("2022-11-03"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//            service.saveFood(new Food(
//                    114,
//                    "Keo cu do",
//                    "v1636225851/hanoi_food_bank_project/uploaded_food/Others/keocudo2_etqdvv.jpg",
//                    "v1636225851/hanoi_food_bank_project/uploaded_food/Others/keocudo2_etqdvv.jpg,v1636225850/hanoi_food_bank_project/uploaded_food/Others/keocudo1_ywnu4a.jpg,v1636225850/hanoi_food_bank_project/uploaded_food/Others/keocudo3_govy5f.jpg,",
//                    "Keo cu do that ngon",
//                    "Keo cu do dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-10"),
//                    Utilities.convertStringToLong("2022-11-05"),
//                    Utilities.convertStringToLong("2022-11-05"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//            service.saveFood(new Food(
//                    115,
//                    "Nem chua Thanh Hoa",
//                    "v1636225851/hanoi_food_bank_project/uploaded_food/Others/nemchuathanhhoa1_yr4nun.jpg",
//                    "v1636225851/hanoi_food_bank_project/uploaded_food/Others/nemchuathanhhoa1_yr4nun.jpg,v1636225850/hanoi_food_bank_project/uploaded_food/Others/nemchuathanhhoa3_hp4dk1.jpg,v1636225850/hanoi_food_bank_project/uploaded_food/Others/nemchuathanhhoa2_vauehc.jpg,",
//                    "Nem chua Thanh Hoa that ngon",
//                    "Nem chua Thanh Hoa dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-09"),
//                    Utilities.convertStringToLong("2022-11-09"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//            service.saveFood(new Food(
//                    116,
//                    "Trung chien",
//                    "v1636225850/hanoi_food_bank_project/uploaded_food/Others/friedegg3_hdf4sg.jpg",
//                    "v1636225850/hanoi_food_bank_project/uploaded_food/Others/friedegg3_hdf4sg.jpg,v1636225850/hanoi_food_bank_project/uploaded_food/Others/friedegg2_objouf.jpg,v1636225849/hanoi_food_bank_project/uploaded_food/Others/friedegg1_yr6unj.jpg,",
//                    "Trung chien that ngon",
//                    "Trung chien dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-08"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//            service.saveFood(new Food(
//                    117,
//                    "Chao long",
//                    "v1636225849/hanoi_food_bank_project/uploaded_food/Others/chaolong1_dwrifg.jpg",
//                    "v1636225849/hanoi_food_bank_project/uploaded_food/Others/chaolong1_dwrifg.jpg,v1636225849/hanoi_food_bank_project/uploaded_food/Others/chaolong3_wlgfam.jpg,v1636225848/hanoi_food_bank_project/uploaded_food/Others/chaolong2_nv7qbr.jpg,",
//                    "Chao long that ngon",
//                    "Chao long dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-07"),
//                    Utilities.convertStringToLong("2022-11-07"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//            service.saveFood(new Food(
//                    118,
//                    "Trung",
//                    "v1636225849/hanoi_food_bank_project/uploaded_food/Others/egg3_ahz5hz.jpg",
//                    "v1636225849/hanoi_food_bank_project/uploaded_food/Others/egg3_ahz5hz.jpg,v1636225849/hanoi_food_bank_project/uploaded_food/Others/egg2_p5im2y.jpg,v1636225849/hanoi_food_bank_project/uploaded_food/Others/egg1_lvsswd.jpg,",
//                    "Trung that ngon",
//                    "Trung dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-10"),
//                    Utilities.convertStringToLong("2022-11-05"),
//                    Utilities.convertStringToLong("2022-11-05"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//            service.saveFood(new Food(
//                    119,
//                    "Banh dau xanh",
//                    "v1636225848/hanoi_food_bank_project/uploaded_food/Others/banhdauxanh3_u2iclf.jpg",
//                    "v1636225848/hanoi_food_bank_project/uploaded_food/Others/banhdauxanh3_u2iclf.jpg,v1636225847/hanoi_food_bank_project/uploaded_food/Others/banhdauxanh2_melkf8.jpg,v1636225847/hanoi_food_bank_project/uploaded_food/Others/banhdauxanh1_upamgd.jpg,",
//                    "Banh dau xanh that ngon",
//                    "Banh dau xanh dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-15"),
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-04"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//            service.saveFood(new Food(
//                    120,
//                    "Banh gai",
//                    "v1636225848/hanoi_food_bank_project/uploaded_food/Others/banhgai2_zkcz8q.png",
//                    "v1636225848/hanoi_food_bank_project/uploaded_food/Others/banhgai2_zkcz8q.png,v1636225847/hanoi_food_bank_project/uploaded_food/Others/banhgai1_rndncg.jpg,v1636225847/hanoi_food_bank_project/uploaded_food/Others/banhgai3_tzlamr.jpg,",
//                    "Banh gai that ngon",
//                    "Banh gai dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-09"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    Utilities.convertStringToLong("2022-11-06"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//            service.saveFood(new Food(
//                    121,
//                    "Trung luoc",
//                    "v1636225848/hanoi_food_bank_project/uploaded_food/Others/boiledegg3_hap9ta.jpg",
//                    "v1636225848/hanoi_food_bank_project/uploaded_food/Others/boiledegg3_hap9ta.jpg,v1636225848/hanoi_food_bank_project/uploaded_food/Others/boiledegg1_dgcjyc.jpg,v1636225848/hanoi_food_bank_project/uploaded_food/Others/boiledegg2_rqvxoy.jpg,",
//                    "Trung luoc that ngon",
//                    "Trung luoc dinh cua chop day",
//                    Utilities.convertStringToLong("2022-11-04"),
//                    Utilities.convertStringToLong("2022-11-03"),
//                    Utilities.convertStringToLong("2022-11-03"),
//                    5,
//                    5,
//                    service.findByCategoryId(12),
//                    12
//            ));
//
//            ================================================================================================================================================
//
//            // Donation
//            service.saveDonation(new Donation(
//                    1,
//                    "Anh phương",
//                    "0986527369",
//                    10.0,
//                    "Thêm chút động lực cho ae",
//                    Utilities.convertStringToLong("2022-08-04"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    2,
//                    "Chị Hòa Đồng Nai",
//                    "0358254954",
//                    21.0,
//                    "Tặng cho các bạn nè",
//                    Utilities.convertStringToLong("2022-08-05"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    3,
//                    "Anh Hào Quảng Ninh",
//                    "0902515566",
//                    13.0,
//                    "meme",
//                    Utilities.convertStringToLong("2022-08-06"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    4,
//                    "Trần Anh Kiên",
//                    "0823444489",
//                    5.0,
//                    "Ẩn danh",
//                    Utilities.convertStringToLong("2022-08-07"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    5,
//                    "Hiên Hạnh",
//                    "0825354567",
//                    1.0,
//                    "Thêm chút động lực cho ae",
//                    Utilities.convertStringToLong("2022-08-08"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    6,
//                    "Khiêm",
//                    "0358254954",
//                    2.0,
//                    "Nhận đi các bạn",
//                    Utilities.convertStringToLong("2022-08-09"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    7,
//                    "Hảo Hán",
//                    "0901836742",
//                    16.0,
//                    "Sốc nhé",
//                    Utilities.convertStringToLong("2022-08-10"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    8,
//                    "Chị Thương Ha noi",
//                    "0334445555",
//                    27.0,
//                    "Thêm bát cơm",
//                    Utilities.convertStringToLong("2022-08-11"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    9,
//                    "Anh Thiện",
//                    "0965635222",
//                    35.0,
//                    "giúp đỡ mọi người thêm nhé",
//                    Utilities.convertStringToLong("2022-08-12"),
//                    1
//            ));
//
//
//
//            service.saveDonation(new Donation(
//                    10,
//                    "Anh phương",
//                    "0986527369",
//                    9.0,
//                    "Tặng cho các bạn nè",
//                    Utilities.convertStringToLong("2022-08-13"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    11,
//                    "Chị Hòa Đồng Nai",
//                    "0358254954",
//                    2.0,
//                    "Thêm chút động lực cho ae",
//                    Utilities.convertStringToLong("2022-08-14"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    12,
//                    "Thơm thơm",
//                    "0388374623",
//                    5.0,
//                    "Thơm tặng cả nhà",
//                    Utilities.convertStringToLong("2022-08-15"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    13,
//                    "Huỳnh Anh",
//                    "0987773332",
//                    3.0,
//                    "có chút",
//                    Utilities.convertStringToLong("2022-08-16"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    14,
//                    "Anh Minh",
//                    "0363647333",
//                    37.0,
//                    "sơn",
//                    Utilities.convertStringToLong("2022-08-17"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    15,
//                    "Hạ thương",
//                    "0986527369",
//                    14.0,
//                    "hơm hơm",
//                    Utilities.convertStringToLong("2022-08-18"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    16,
//                    "Rạch giá",
//                    "0373464622",
//                    6.5,
//                    "Tặng cho các bạn nè",
//                    Utilities.convertStringToLong("2022-08-19"),
//                    1
//            ));
//
//
//
//            service.saveDonation(new Donation(
//                    17,
//                    "Minh Anh",
//                    "0986527374",
//                    19.0,
//                    "doping",
//                    Utilities.convertStringToLong("2022-08-20"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    18,
//                    "Hạ My",
//                    "0387474322",
//                    12.0,
//                    "Tặng cho các bạn nè",
//                    Utilities.convertStringToLong("2022-08-21"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    19,
//                    "Gia bảo",
//                    "0966636365",
//                    10.0,
//                    "doping",
//                    Utilities.convertStringToLong("2022-08-22"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    20,
//                    "Khánh Ngân",
//                    "0963652453",
//                    28.0,
//                    "doping",
//                    Utilities.convertStringToLong("2022-08-23"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    21,
//                    "Mạnh",
//                    "09888888844",
//                    14.0,
//                    "phải nhận",
//                    Utilities.convertStringToLong("2022-08-24"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    22,
//                    "Mèo Con",
//                    "0358254954",
//                    12.0,
//                    "thêm hạt",
//                    Utilities.convertStringToLong("2022-08-25"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    23,
//                    "Gia Bảo",
//                    "0986527369",
//                    14.0,
//                    "kiên nhận",
//                    Utilities.convertStringToLong("2022-08-26"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    24,
//                    "Hạnh Phúc",
//                    "0399993456",
//                    12.0,
//                    "Tặng cho các bạn nè",
//                    Utilities.convertStringToLong("2022-08-27"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    25,
//                    "KC Cường",
//                    "0990333344",
//                    17.0,
//                    "thơm",
//                    Utilities.convertStringToLong("2022-08-28"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    26,
//                    "Dứa",
//                    "0358254954",
//                    24.0,
//                    "thêm động lực",
//                    Utilities.convertStringToLong("2022-08-29"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    27,
//                    "Én Nhỏ",
//                    "0965352521",
//                    24.0,
//                    "iu",
//                    Utilities.convertStringToLong("2022-08-30"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    28,
//                    "YN Trường",
//                    "0353426712",
//                    2.0,
//                    "Tặng cho các bạn nè",
//                    Utilities.convertStringToLong("2022-08-31"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    29,
//                    "Anh phương",
//                    "0986527369",
//                    19.0,
//                    "Thêm chút động lực cho ae",
//                    Utilities.convertStringToLong("2022-11-01"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    30,
//                    "Giai Nhân",
//                    "0343424244",
//                    21.0,
//                    "Tặng cho các bạn nè",
//                    Utilities.convertStringToLong("2022-11-02"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    31,
//                    "Anh phương",
//                    "0986527369",
//                    11.0,
//                    "Thêm chút động lực cho ae",
//                    Utilities.convertStringToLong("2022-11-03"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    32,
//                    "Chị Hòa Đồng Nai",
//                    "0358254954",
//                    14.0,
//                    "Tặng cho các bạn nè",
//                    Utilities.convertStringToLong("2022-11-04"),
//                    1
//            ));
//
//            service.saveDonation(new Donation(
//                    33,
//                    "Chị Hiên",
//                    "0358254954",
//                    23.0,
//                    "doping",
//                    Utilities.convertStringToLong("2022-11-05"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    34,
//                    "Liêm",
//                    "0986527369",
//                    33.0,
//                    "ki ki",
//                    Utilities.convertStringToLong("2022-11-06"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    35,
//                    "Tùng Lâm",
//                    "0358254954",
//                    20.0,
//                    "thêm mắm",
//                    Utilities.convertStringToLong("2022-11-07"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    36,
//                    "Kiều Trinh",
//                    "0986527369",
//                    15.0,
//                    "cộng",
//                    Utilities.convertStringToLong("2022-11-08"),
//                    1
//            ));
//            service.saveDonation(new Donation(
//                    37,
//                    "HH Tình",
//                    "0358254954",
//                    31.0,
//                    "doping",
//                    Utilities.convertStringToLong("2022-11-09"),
//                    1
//            ));

            // Request
//            service.saveRequest(2, 1, "em xin", "2022-08-04", "2022-08-04");
//            service.saveRequest(3, 2, "anh ơi cho em xin món này", "2022-08-05", "2022-08-05");
//            service.saveRequest(2, 3, "cho em xin món này", "2022-08-06", "2022-08-06");
//            service.saveRequest(3, 4, "em xin", "2022-08-07", "2022-08-07");
//            service.saveRequest(2, 5, "món này ngon quá", "2022-08-08", "2022-08-08");
//            service.saveRequest(3, 6, "em xin với ạ", "2022-08-09", "2022-08-09");
//            service.saveRequest(2, 7, "em xin cho bữa tối ạ", "2022-08-10", "2022-08-10");
//            service.saveRequest(3, 8, "em cho bé bán vé số ạ", "2022-08-11", "2022-08-11");
//            service.saveRequest(2, 9, "help", "2022-08-12", "2022-08-12");
//            service.saveRequest(3, 10, "em xin với ạ", "2022-08-13", "2022-08-13");
//
//            service.saveRequest(2, 17, "cho em xin bữa này với ạ", "2022-08-14", "2022-08-14");
//            service.saveRequest(3, 18, "nhìn ngon quá cho em xin món này vs ạ", "2022-08-15", "2022-08-15");
//            service.saveRequest(2, 19, "món này ngon quá", "2022-08-16", "2022-08-16");
//            service.saveRequest(3, 20, "em xin với ạ", "2022-08-17", "2022-08-17");
//
//            service.saveRequest(2, 42, "món này ngon quá", "2022-08-18", "2022-08-18");
//            service.saveRequest(3, 43, "cho em xin món này đi đại ca", "2022-08-19", "2022-08-19");
//            service.saveRequest(2, 44, "cho em xin bữa này với ạ", "2022-08-20", "2022-08-20");
//            service.saveRequest(3, 45, "em xin với ạ", "2022-08-21", "2022-08-21");
//            service.saveRequest(2, 46, "món này ngon quá", "2022-08-25", "2022-08-25");
//            service.saveRequest(3, 47, "cho em xin món này đi đại ca", "2022-08-26", "2022-08-26");
//
//            service.saveRequest(2, 81, "em xin", "2022-11-02", "2022-11-02");
//            service.saveRequest(3, 81, "anh ơi cho em xin món này", "2022-11-02", "2022-11-02");
//
//            service.saveRequest(2, 82, "cho em xin món này", "2022-11-03", "2022-11-03");
//            service.saveRequest(3, 82, "em xin", "2022-11-03", "2022-11-03");
//
//            service.saveRequest(2, 83, "món này ngon quá", "2022-11-04", "2022-11-04");
//            service.saveRequest(3, 83, "em xin với ạ", "2022-11-04", "2022-11-04");
//
//            service.saveRequest(2, 84, "em xin cho bữa tối ạ", "2022-11-05", "2022-11-05");
//            service.saveRequest(3, 84, "em cho bé bán vé số ạ", "2022-11-05", "2022-11-05");
//
//            service.saveRequest(2, 85, "help", "2022-11-06", "2022-11-06");
//            service.saveRequest(3, 85, "em xin với ạ", "2022-11-06", "2022-11-06");
//
//            service.saveRequest(2, 86, "cho em xin bữa này với ạ", "2022-11-07", "2022-11-07");
//            service.saveRequest(3, 86, "nhìn ngon quá cho em xin món này vs ạ", "2022-11-07", "2022-11-07");
//
//            service.saveRequest(2, 87, "món này ngon quá", "2022-11-08", "2022-11-08");
//            service.saveRequest(3, 87, "em xin với ạ", "2022-11-08", "2022-11-08");
//
//            service.saveRequest(2, 88, "món này ngon quá", "2022-11-09", "2022-11-09");
//            service.saveRequest(3, 88, "cho em xin món này đi đại ca", "2022-11-09", "2022-11-09");
//
//            service.saveRequest(2, 89, "cho em xin bữa này với ạ", "2022-11-10", "2022-11-10");
//            service.saveRequest(3, 89, "em xin với ạ", "2022-11-10", "2022-11-10");
//
//            service.saveRequest(2, 90, "món này ngon quá", "2022-11-07", "2022-11-07");
//            service.saveRequest(3, 90, "cho em xin món này đi đại ca", "2022-11-07", "2022-11-07");
//
//            service.saveRequest(2, 91, "món này ngon quá", "2022-11-03", "2022-11-03");
//            service.saveRequest(3, 91, "cho em xin món này đi đại ca", "2022-11-03", "2022-11-03");
//
//            service.saveRequest(2, 92, "cho em xin bữa này với ạ", "2022-11-04", "2022-11-04");
//            service.saveRequest(3, 92, "em xin với ạ", "2022-11-04", "2022-11-04");
//
//            service.saveRequest(2, 93, "món này ngon quá", "2022-11-05", "2022-11-05");
//            service.saveRequest(3, 93, "em xin với ạ", "2022-11-05", "2022-11-05");
//
//            service.saveRequest(2, 94, "cho em xin bữa này với ạ", "2022-11-07", "2022-11-07");
//            service.saveRequest(3, 94, "cho em xin món này đi đại ca", "2022-11-07", "2022-11-07");
//
//            service.saveRequest(2, 95, "món này ngon quá", "2022-11-08", "2022-11-08");
//            service.saveRequest(3, 95, "em xin với ạ", "2022-11-08", "2022-11-08");
//
//            service.saveRequest(2, 96, "cho em xin bữa này với ạ", "2022-11-08", "2022-11-08");
//            service.saveRequest(3, 96, "cho em xin món này đi đại ca", "2022-11-08", "2022-11-08");
//
//            service.saveRequest(2, 97, "cho em xin bữa này với ạ", "2022-11-03", "2022-11-03");
//            service.saveRequest(3, 97, "nhìn ngon quá cho em xin món này vs ạ", "2022-11-03", "2022-11-03");
//
//            service.saveRequest(2, 98, "cho em xin bữa này với ạ", "2022-11-08", "2022-11-08");
//            service.saveRequest(3, 98, "cho em xin món này đi đại ca", "2022-11-08", "2022-11-08");
//
//            service.saveRequest(2, 99, "món này ngon quá", "2022-11-04", "2022-11-04");
//            service.saveRequest(3, 99, "em xin với ạ", "2022-11-04", "2022-11-04");
//
//            service.saveRequest(2, 100, "cho em xin bữa này với ạ", "2022-11-03", "2022-11-03");
//            service.saveRequest(3, 100, "nhìn ngon quá cho em xin món này vs ạ", "2022-11-04", "2022-11-04");
//
//            service.saveRequest(2, 101, "nhìn ngon quá cho em xin món này vs ạ", "2022-11-12", "2022-11-12");
//            service.saveRequest(3, 101, "nhìn ngon quá", "2022-11-11", "2022-11-11");
//
//            service.saveRequest(2, 102, "món này ngon quá", "2022-11-11", "2022-11-11");
//            service.saveRequest(3, 102, "cho em xin bữa này với ạ", "2022-11-11", "2022-11-11");
//
//            service.saveRequest(2, 103, "món này ngon quá", "2022-11-10", "2022-11-10");
//            service.saveRequest(3, 103, "em xin với ạ", "2022-11-11", "2022-11-11");
//
//            service.saveRequest(2, 104, "nhìn ngon quá cho em xin món này vs ạ", "2022-11-10", "2022-11-10");
//            service.saveRequest(3, 104, "em xin với ạ", "2022-11-10", "2022-11-10");
//
//            service.saveRequest(2, 105, "nhìn ngon quá cho em xin món này vs ạ", "2022-11-08", "2022-11-08");
//            service.saveRequest(3, 105, "cho em xin bữa này với ạ", "2022-11-08", "2022-11-08");
//
//            service.saveRequest(2, 106, "món này ngon quá", "2022-11-08", "2022-11-08");
//            service.saveRequest(3, 106, "nhìn ngon quá cho em xin món này vs ạ", "2022-11-08", "2022-11-08");
//
//            // Feedback
//            service.saveFeedback(1, "image", "content", 100, 1, 2, 1);
//            service.saveFeedback(1, "v1636225794/hanoi_food_bank_project/uploaded_food/Fruit/xoai3_dwlb0l.jpg",
//                    "xoài rất thơm, cho 1 like", 99, 2, 5, 2);
//            service.saveFeedback(2, "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/thanhlong1_wh9zfe.jpg",
//                    "content", 90, 2, 5, 2);
//            service.saveFeedback(3, "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/roi3_qu2vv8.png",
//                    "content", 88, 2, 5, 2);
//            service.saveFeedback(4, "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/tao3_ghdoug.jpg",
//                    "Táo ròn và ngọt", 100, 2, 5, 3);
//            service.saveFeedback(5, "v1636225792/hanoi_food_bank_project/uploaded_food/Fruit/mit3_frlbub.jpg",
//                    "Mít thơm quá", 93, 2, 5, 2);
//            service.saveFeedback(6, "v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/chomchom2_updkwz.jpg",
//                    "còn không các bạn", 91, 2, 5, 3);
//            service.saveFeedback(7, "v1636225790/hanoi_food_bank_project/uploaded_food/Fruit/khe2_tkcxpz.jpg",
//                    "lâu lắm rồi mới lại ăn khế ngọt vậy", 99, 2, 5, 2);
//            service.saveFeedback(8, "v1636225789/hanoi_food_bank_project/uploaded_food/Fruit/chanhday1_neq9fr.jpg",
//                    "Cảm ơn nhé", 85, 2, 5, 2);
//            service.saveFeedback(9, "v1636225789/hanoi_food_bank_project/uploaded_food/Fruit/dautay3_xvfgbz.jpg",
//                    "Thanks", 80, 2, 5, 2);
//            service.saveFeedback(10, "v1636225788/hanoi_food_bank_project/uploaded_food/Fruit/banana3_aj8e6i.jpg",
//                    "lần sau lại cho mk nhé", 99, 2, 5, 2);
//            service.saveFeedback(11, "v1636225761/hanoi_food_bank_project/uploaded_food/Fast%20Food/pizza2_zd7dtl.png",
//                    "wow", 84, 2, 5, 2);
//            service.saveFeedback(12, "v1636225761/hanoi_food_bank_project/uploaded_food/Fast%20Food/xucxich2_ldcmlf.jpg",
//                    "hot hot", 92, 2, 5, 2);
//            service.saveFeedback(13, "v1636225759/hanoi_food_bank_project/uploaded_food/Fast%20Food/khoaitaylac3_ckgctu.png",
//                    "lần đầu ăn, ngon quá", 98, 2, 5, 3);
//            service.saveFeedback(14, "v1636225759/hanoi_food_bank_project/uploaded_food/Fast%20Food/xienthitnuong1_aoombt.jpg",
//                    "bạn nay ngoan", 88, 1, 5, 2);
//            service.saveFeedback(15, "v1636225758/hanoi_food_bank_project/uploaded_food/Fast%20Food/sandwich3_veett9.jpg",
//                    "ok", 93, 1, 5, 2);
//            service.saveFeedback(16, "v1636225757/hanoi_food_bank_project/uploaded_food/Fast%20Food/nemchuanuong2_hlvasd.jpg",
//                    "lại ok", 78, 1, 5, 4);
//            service.saveFeedback(17, "v1636225756/hanoi_food_bank_project/uploaded_food/Fast%20Food/garan2_yoaeq2.jpg",
//                    "rất ngoan chúc ngon miệng", 90, 1, 5, 2);
//            service.saveFeedback(18, "v1636225756/hanoi_food_bank_project/uploaded_food/Fast%20Food/hamburger3_nqmtvf.jpg",
//                    "vẫn còn nóng hổi", 100, 1, 5, 4);
//            service.saveFeedback(19, "v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/cavienchien1_cf2pgn.png",
//                    "ăn ngon nhé", 100, 1, 5, 2);
//            service.saveFeedback(20, "v1636225755/hanoi_food_bank_project/uploaded_food/Fast%20Food/donuts1_vfwgsu.jpg",
//                    "cảm ơn", 96, 2, 5, 2);
//            service.saveFeedback(21, "v1636225828/hanoi_food_bank_project/uploaded_food/Snacks/snackrongbien3_c0ysfm.png",
//                    "chúc ngon miêngj", 82, 1, 5, 2);
//            service.saveFeedback(22, "v1636225826/hanoi_food_bank_project/uploaded_food/Snacks/snackphomai2_qjglw7.jpg",
//                    "ngậy ngậy", 87, 1, 5, 2);
//            service.saveFeedback(23, "v1636225826/hanoi_food_bank_project/uploaded_food/Snacks/snackmuc1_ayza92.jpg",
//                    "haha", 97, 2, 5, 3);
//            service.saveFeedback(24, "v1636225825/hanoi_food_bank_project/uploaded_food/Snacks/snackkhoaitay2_wwbsde.jpg",
//                    "rất ngon ạ", 99, 1, 5, 4);
//            service.saveFeedback(25, "v1636225824/hanoi_food_bank_project/uploaded_food/Snacks/snackhanh3_f7f3mf.jpg",
//                    "okay", 100, 1, 5, 3);
//            service.saveFeedback(26, "v1636225824/hanoi_food_bank_project/uploaded_food/Snacks/snackcua3_yfzv5r.jpg",
//                    "cảm ơn", 91, 2, 5, 2);
//            service.saveFeedback(27, "v1636225823/hanoi_food_bank_project/uploaded_food/Snacks/snackbido2_eh2tnw.jpg",
//                    "đỉnh cao", 93, 1, 5, 3);
//            service.saveFeedback(28, "v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/phongtom2_kocvjd.jpg",
//                    "Được của nó", 89, 2, 5, 4);
//            service.saveFeedback(29, "v1636225821/hanoi_food_bank_project/uploaded_food/Snacks/khoaitayque1_jywhvn.jpg",
//                    "rất dài và mềm", 91, 1, 5, 4);
//            service.saveFeedback(30, "v1636225822/hanoi_food_bank_project/uploaded_food/Snacks/snackbapngot3_a7qf9y.jpg",
//                    "thơm mùi bơ", 85, 2, 5, 2);
//            service.saveFeedback(31, "v1636225854/hanoi_food_bank_project/uploaded_food/Others/salat3_lw1tgv.jpg",
//                    "ăn ngon nhé", 86, 1, 5, 2);
//            service.saveFeedback(32, "v1636225852/hanoi_food_bank_project/uploaded_food/Others/phocuon2_myzqcv.png",
//                    "rất nhiều thịt heo", 99, 1, 5, 3);
//            service.saveFeedback(33, "v1636225851/hanoi_food_bank_project/uploaded_food/Others/keocudo2_etqdvv.jpg",
//                    "tuổi thơ dữ dội", 100, 1, 5, 2);
//            service.saveFeedback(34, "v1636225851/hanoi_food_bank_project/uploaded_food/Others/nemchuathanhhoa1_yr4nun.jpg",
//                    "có lại cho", 93, 1, 5, 3);
//            service.saveFeedback(35, "v1636225850/hanoi_food_bank_project/uploaded_food/Others/friedegg3_hdf4sg.jpg",
//                    "ngon quá", 100, 2, 5, 2);

        };
    }

}
