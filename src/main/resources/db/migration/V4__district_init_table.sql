INSERT INTO district(id, name_uz, name_en, name_ru, region_id, created_date, visible, county)
values
    -- Tashkent Shahar
    (1, 'Bektemir', 'Bektemir', 'Бектемир',  1, now(), true, 'Bektemir Tumani'),
    (2, 'Mirobod', 'Mirabad', 'Мирабад',  1, now(), true, 'Mirobod Tumani'),
    (3, 'Mirzo Ulug‘bek', 'Mirzo Ulugbek', 'Мирзо-Улугбек',  1, now(), true, 'Mirzo Ulug‘bek Tumani'),
    (4, 'Sergeli', 'Sergely', 'Сергелий',  1, now(), true, 'Sergeli Tumani'),
    (5, 'Yangihayot', 'Yangihayat', 'Янги хаёт',  1, now(), true, 'Yangihayot Tumani'),
    (6, 'Olmazor', 'Almazar', 'Олмазор',  1, now(), true, 'Olmazor Tumani'),
    (7, 'Uchtepa', 'Uchtepa', 'Учтепa',  1, now(), true, 'Uchtepa Tumani'),
    (8, 'Shayxontohur', 'Shaikhontohur', 'Шайхонтохур',  1, now(), true, 'Shayxontohur Tumani'),
    (9, 'Yashnobod', 'Yashnabad', 'Яшнобод',  1, now(), true, 'Yashnobod Tumani'),
    (10, 'Chilonzor', 'Chilanzar', 'Чиланзар',  1, now(), true, 'Chilonzor Tumani'),
    (11, 'Yunusobod', 'Yunusabad', 'Юнусабад',  1, now(), true, 'Yunusobod Tumani'),
    (12, 'Yakkasaroy', 'Yakkasaray', 'Яккасарай',  1, now(), true, 'Yakkasaroy Tumani'),

    -- Toshkent viloyati
    (13, 'Bekobod', 'Bekabad', 'Бекабад',  2, now(), true, 'Bekobod Tumani'),
    (14, 'Boʻstonliq', 'Bostonian', 'Бостанлык',  2, now(), true, 'Boʻstonliq Tumani'),
    (15, 'Boʻka', 'Boka', 'Букинский',  2, now(), true, 'Boʻka Tumani'),
    (16, 'Chinoz', 'Chinaz', 'Чиназ',  2, now(), true, 'Chinoz Tumani'),
    (17, 'Qibray', 'Kibrai', 'Кибрай',  2, now(), true, 'Qibray Tumani'),
    (18, 'Ohangaron', 'Ohangaron', 'Ахангаранский',  2, now(), true, 'Ohangaron Tumani'),
    (19, 'Oqqoʻrgʻon', 'Okkorgan', 'Аккурган',  2, now(), true, 'Oqqoʻrgʻon Tumani'),
    (20, 'Parkent', 'Parkent', 'Паркент', 2, now(), true, 'Parkent Tumani'),
    (21, 'Piskent', 'Piskent', 'Пскент',  2, now(), true, 'Piskent Tumani'),
    (22, 'Quyi Chirchiq', 'Kuyi Chirchik', 'Куйичирчик',  2, now(), true, 'Quyi Chirchiq Tumani'),
    (23, 'Oʻrta Chirchiq', 'Urta Chirchik', 'Уртачирчик',  2, now(), true, 'Oʻrta Chirchiq Tumani'),
    (24, 'Yangiyoʻl', 'Yangiyul', 'Янгиюльский', 2, now(), true, 'Yangiyoʻl Tumani'),
    (25, 'Yuqori Chirchiq', 'Yukori Chirchik', 'Верхний Чирчик',  2, now(), true,
     'Yuqori Chirchiq Tumani'),
    (26, 'Zangiota', 'Zangiata', 'Зангиатa',  2, now(), true, 'Zangiota Tumani'),
    (27, 'Angren', 'Angren', 'Ангрен',  2, now(), true, 'Angren Tumani'),


-- Andijon
    (28, 'Oltinkoʻl', 'Oltinkul', 'Алтынкуль',  3, now(), true, 'Oltinkoʻl Tumani')
        ,
    (29, 'Andijon', 'Andijan', 'Андижан',  3, now(), true, 'Andijon Tumani'),
    (30, 'Asaka', 'Asaka', 'Асака',  3, now(), true, 'Asaka Tumani'),
    (31, 'Baliqchi', 'Balikchi', 'Балыкчи',  3, now(), true, 'Baliqchi Tumani'),
    (32, 'Boʻz', 'Buz', 'Боз',  3, now(), true, 'Boʻz Tumani'),
    (33, 'Buloqboshi', 'Bulokboshi', 'Булакбаши',  3, now(), true, 'Buloqboshi Tumani'),
    (34, 'Qorasuv', 'Korasuv', 'Карасу',  3, now(), true, 'Qorasuv Tumani'),
    (35, 'Qoʻrgʻontepa', 'Kurgontepa', 'Кургантепа',  3, now(), true, 'Qoʻrgʻontepa Tumani'),
    (36, 'Marhamat', 'Marhamat', 'Мархамат',  3, now(), true, 'Marhamat Tumani'),
    (37, 'Poytug', 'Poytug', 'Пайтуг', 3, now(), true, 'Poytug Tumani'),
    (38, 'Paxtaobod', 'Paxtaobod', 'Пахтабад',  3, now(), true, 'Paxtaobod Tumani'),
    (39, 'Shahrixon', 'Shahrihan', 'Шахрихан',  3, now(), true, 'Shahrixon Tumani'),
    (40, 'Xonobod', 'Honabad', 'Ханабад',  3, now(), true, 'Xonobod Tumani'),
    (41, 'Xoʻjaobod', 'Hujabod', 'Ходжаабад', 3, now(), true, 'Xoʻjaobod Tumani'),
    (42, 'Jalaquduq', 'Jalaqoduk', 'Жалакудук',  3, now(), true, 'Jalaquduq Tumani'),
    (43, 'Ulug‘nor', 'Ulugnor', 'Улугнор',  3, now(), true, 'Ulug‘nor Tumani'),



-- Buxoro viloyati
    (44, 'Olot', 'Olot', 'Алат',  4, now(), true, 'Olot Tumani'),
    (45, 'Buxoro', 'Bukhara', 'Бухара',  4, now(), true, 'Buxoro Tumani'),
    (46, 'Peshku', 'Peshku', 'Пешку',  4, now(), true, 'Peshku Tumani'),
    (47, 'Gʻijduvon', 'Gijduvan', 'Гиждуван',  4, now(), true, 'Gʻijduvon Tumani'),
    (48, 'Kogon', 'Kogon', 'Каган',  4, now(), true, 'Kogon Tumani'),
    (49, 'Qorakoʻl', 'Korakul', 'Каракуль',  4, now(), true, 'Qorakoʻl Tumani'),
    (50, 'Qorovulbozor', 'Korovulbazar', 'Караулбазар',  4, now(), true, 'Qorovulbozor Tumani'),
    (51, 'Romitan', 'Romitan', 'Ромитан',  4, now(), true, 'Romitan Tumani'),
    (52, 'Shofirkon', 'Shofirkor', 'Шафиркан',  4, now(), true, 'Shofirkon Tumani'),
    (53, 'Vobkent', 'Vobkent', 'Вабкент',  4, now(), true, 'Vobkent Tumani'),
    (54, 'Jondor', 'Jondor', 'Жондор',  4, now(), true, 'Jondor Tumani'),

-- Jizzax viloyati
    (55, 'Arnasoy', 'Arnasoy', 'Арнасай',  5, now(), true, 'Arnasoy Tumani'),
    (56, 'Baxmal', 'Bahmal', 'Бахмал',  5, now(), true, 'Baxmal Tumani'),
    (57, 'Doʻstlik', 'Dustlik', 'Дустлик',  5, now(), true, 'Doʻstlik Tumani'),
    (58, 'Jizzax', 'Jizakh', 'Джизак',  5, now(), true, 'Jizzax Tumani'),
    (59, 'Sharof Rashidov', 'Sharof Rashidov', 'Шаров Рашидов',  5, now(), true,
     'Sharof Rashidov Tumani'),
    (60, 'Gʻallaorol', 'Gallaorol', 'Галлаарал',  5, now(), true, 'Gʻallaorol Tumani'),
    (61, 'Mirzacho‘l', 'Mirzachul', 'Мирзачол',  5, now(), true, 'Mirzacho‘l Tumani'),
    (62, 'Paxtakor', 'Pakhtakor', 'Пахтакор',  5, now(), true, 'Paxtakor Tumani'),
    (63, 'Yangiobod', 'Yangiobod', 'Янгабад',  5, now(), true, 'Yangiobod Tumani'),
    (64, 'Forish', 'Forish', 'Фориш',  5, now(), true, 'Forish Tumani'),
    (65, 'Zomin', 'Zomin', 'Заамин',  5, now(), true, 'Zomin Tumani'),
    (66, 'Zafarobod', 'Zafarobod', 'Зафарабад',  5, now(), true, 'Zafarobod Tumani'),
    (67, 'Zarbdor', 'Zarbdor', 'Зарбдар',  5, now(), true, 'Zarbdor Tumani'),
-- Qoraqalpog'iston
    (68, 'Bo‘zatov', 'Buzatov', 'Бозатаусский', 6, now(), true, 'Bo‘zatov rayonı'),
    (69, 'Beruniy', 'Beruni', 'Беруни', 6, now(), true, 'Beruniy rayonı'),
    (70, 'Amudaryo', 'Amudarya', 'Амударьинский',  6, now(), true, 'Amudaryo rayonı'),
    (71, 'Chimboy', 'Chimbay', 'Чимбай',  6, now(), true, 'Chimboy rayonı'),
    (72, 'Qonlikoʻl', 'Konlikul', 'Канлыкуль',  6, now(), true, 'Qonlikoʻl rayonı'),
    (73, 'Qoraoʻzak', 'Koraozak', 'Караузяк',  6, now(), true, 'Qoraoʻzak rayonı'),
    (74, 'Kegeyli', 'Kegeyli', 'Кегейли',  6, now(), true, 'Kegeyli rayonı'),
    (75, 'Qoʻngʻirot', 'Kongirot', 'Кунград',  6, now(), true, 'Qoʻngʻirot rayonı'),
    (76, 'Ellikqal‘a', 'Ellikkala', 'Элликкалинский',  6, now(), true, 'Ellikqal‘a rayonı'),
    (77, 'Moʻynoq', 'Muynak', 'Муйнак',  6, now(), true, 'Moʻynoq rayonı'),
    (78, 'Nukus', 'Nukus', 'Нукус',  6, now(), true, 'Nukus rayonı'),
    (79, 'Shumanay', 'Shumanay', 'Шуманай',  6, now(), true, 'Shumanay rayonı'),
    (80, 'Taxtakoʻpir', 'Takhtakupir', 'Тахтакупыр',  6, now(), true, 'Taxtakoʻpir rayonı'),
    (81, 'Toʻrtkoʻl', 'Turtkul', 'Турткуль',  6, now(), true, 'Toʻrtkoʻl rayonı'),
    (82, 'Xoʻjayli', 'Kxujayli', 'Ходжейли', 6, now(), true, 'Xoʻjayli rayonı'),

-- Qashqadaryo
    (83, 'Chiroqchi', 'Chirakchi', 'Чиракчи', 7, now(), true, 'Chiroqchi Tumani'),
    (84, 'Dehqonobod', 'Dehkanabad', 'Дехканабад',  7, now(), true, 'Dehqonobod Tumani'),
    (85, 'Gʻuzor', 'Guzar', 'Гузар',  7, now(), true, 'Gʻuzor Tumani'),
--     (86, 'Qamashi', 'Kamashi', 'Камаши', 'Қамаши', 7, now(), true, 'Qamashi Tumani'),
    (87, 'Qarshi', 'Karshi', 'Карши',  7, now(), true, 'Qarshi Tumani'),
    (88, 'Koson', 'Koson', 'Касан',  7, now(), true, 'Koson Tumani'),
    (89, 'Kasbi', 'Kasbi', 'Касби',  7, now(), true, 'Kasbi Tumani'),
    (90, 'Kitob', 'Kitab', 'Китаб',  7, now(), true, 'Kitob Tumani'),
    (91, 'Muborak', 'Muborak', 'Мубарек', 7, now(), true, 'Muborak Tumani'),
    (92, 'Qamashi', 'Kamashi', 'Камаши',  7, now(), true, 'Qamashi Tumani'),
    (93, 'Shahrisabz', 'Shahrisabz', 'Шахрисабз',  7, now(), true, 'Shahrisabz Tumani'),
    (94, 'Ko‘kdala', 'Kukdala', 'Кокдала',  7, now(), true, 'Ko‘kdala Tumani'),
    (95, 'Yakkabog', 'Yakkabag', 'Яккабаг',  7, now(), true, 'Yakkabog’ Tumani'),
    (96, 'Mirishkor', 'Mirishkor', 'Миришкор',  7, now(), true, 'Mirishkor Tumani'),
    (97, 'Nishon', 'Nishan', 'Нишан',  7, now(), true, 'Nishon Tumani'),


-- Navoiy viloyati
    (98, 'Navbahor', 'Navbahor', 'Навбахар',  8, now(), true, 'Navbahor Tumani'),
    (99, 'Konimex', 'Konimekx', 'Канимех',  8, now(), true, 'Konimex Tumani'),
    (100, 'Karmana', 'Karmana', 'Кармана',  8, now(), true, 'Karmana Tumani'),
    (101, 'Qiziltepa', 'Kiziltepa', 'Кызылтепа',  8, now(), true, 'Qiziltepa Tumani'),
    (102, 'Navoiy', 'Navai', 'Навои',  8, now(), true, 'Navoiy Tumani'),
    (103, 'Nurota', 'Nurata', 'Нурата',  8, now(), true, 'Nurota Tumani'),
    (104, 'Tomdi', 'Tomdi', 'Тамды',  8, now(), true, 'Tomdi Tumani'),
    (105, 'Uchquduq', 'Uchkuduk', 'Учкудук',  8, now(), true, 'Uchquduq Tumani'),
    (106, 'Xatirchi', 'Khatirchi', 'Хатирчи',  8, now(), true, 'Xatirchi Tumani'),

-- Namangan viloyati
    (107, 'Chortoq', 'Chortak', 'Чартак', 9, now(), true, 'Chortoq Tumani'),
    (108, 'Davlatobod', 'Davlatobod', 'Давлатабад',  9, now(), true, 'Davlatobod Tumani'),
    (109, 'Chust', 'Chust', 'Чуст',  9, now(), true, 'Chust Tumani'),
    (110, 'Yangiqo‘rg‘on', 'Yangikurgan', 'Янгикорган',  9, now(), true, 'Yangiqo‘rg‘on Tumani'),
    (111, 'Kosonsoy', 'Kosonsoy', 'Касансай', 9, now(), true, 'Kosonsoy Tumani'),
    (112, 'Namangan', 'Namangan', 'Наманган',  9, now(), true, 'Namangan Tumani'),
    (113, 'Pop', 'Pop', 'Пап',  9, now(), true, 'Pop Tumani'),
    (114, 'Uychi', 'Uychi', 'Уйчи',  9, now(), true, 'Uychi Tumani'),
    (115, 'Toʻraqoʻrgʻon', 'Turakurgon', 'Туракурган',  9, now(), true, 'Toʻraqoʻrgʻon Tumani'),
    (116, 'Uchqoʻrgʻon', 'Uchquduk', 'Учкурган',  9, now(), true, 'Uchqoʻrgʻon Tumani'),
    (117, 'Mingbuloq', 'Mingbulok', 'Минбулаг',  9, now(), true, 'Mingbuloq Tumani'),
    (118, 'Norin', 'Norin', 'Норин',  9, now(), true, 'Norin Tumani'),

-- Samarqand viloyati
    (119, 'Bulungʻur', 'Bulungur', 'Булунгур',  10, now(), true, 'Bulungʻur Tumani'),
    (120, 'Jomboy', 'Jomboy', 'Джамбай', 10, now(), true, 'Jomboy Tumani'),
    (121, 'Narpay', 'Narpay', 'Нарпай',  10, now(), true, 'Narpay Tumani'),
    (122, 'Oqdaryo', 'Okdaryo', 'Акдарьинский',  10, now(), true, 'Oqdaryo Tumani'),
    (123, 'Pastarg‘om', 'Pastargom', 'Пастдаргомский',  10, now(), true, 'Pastarg‘om Tumani'),
    (124, 'Ishtixon', 'Ishtikhan', 'Иштыхан',  10, now(), true, 'Ishtixon Tumani'),
    (125, 'Kattaqoʻrgʻon', 'Kattakorgan', 'Каттакурган',  10, now(), true, 'Kattaqoʻrgʻon Tumani'),
    (126, 'Qoʻshrobod', 'Kushrobod', 'Кушрабад',  10, now(), true, 'Qoʻshrobod Tumani'),
    (127, 'Paxtachi', 'Pakhtachi', 'Пахтачийский',  10, now(), true, 'Paxtachi Tumani'),
    (128, 'Nurobod', 'Nurobod', 'Нурабад',  10, now(), true, 'Nurobod Tumani'),
    (129, 'Payariq', 'Payariq', 'Пайарык',  10, now(), true, 'Payariq Tumani'),
    (130, 'Samarqand', 'Samarkand', 'Самарканд',  10, now(), true, 'Samarqand Tumani'),
    (131, 'Tayloq', 'Taylok', 'Тайлак',  10, now(), true, 'Toyloq Tumani'),
    (132, 'Urgut', 'Urgut', 'Ургут',  10, now(), true, 'Urgut Tumani'),


-- Surxondaryo
    (133, 'Angor', 'Angor', 'Ангор',  11, now(), true, 'Angor Tumani'),
    (134, 'Boysun', 'Boysun', 'Байсун', 11, now(), true, 'Boysun Tumani'),
    (135, 'Bandixon', 'Bandikhan', 'Бандихон',  11, now(), true, 'Bandixon Tumani'),
    (136, 'Denov', 'Denav', 'Денау',  11, now(), true, 'Denov Tumani'),
    (137, 'Jarqoʻrgʻon', 'Jurakurgon', 'Джаркурган',  11, now(), true, 'Jarqoʻrgʻon Tumani'),
    (138, 'Qiziriq', 'Kizirik', 'Кизирик',  11, now(), true, 'Qiziriq Tumani'),
    (139, 'Qumqoʻrgʻon', 'Kumkurgon', 'Кумкурган',  11, now(), true, 'Qumqoʻrgʻon tumani'),
    (140, 'Muzrobod', 'Muzrobod', 'Музрабад',  11, now(), true, 'Muzrobod Tumani'),
    (141, 'Sariosiyo', 'Sariosiyo', 'Сариасия',  11, now(), true, 'Sariosiyo Tumani'),
    (142, 'Sherobod', 'Sherobod', 'Шерабад',  11, now(), true, 'Sherobod Tumani'),
    (143, 'Shoʻrchi', 'Shurchi', 'Шурчи',  11, now(), true, 'Shoʻrchi Tumani'),
    (144, 'Termiz', 'Termiz', 'Термез',  11, now(), true, 'Termiz Tumani'),
    (145, 'Uzun', 'Uzun', 'Узун',  11, now(), true, 'Uzun tumani'),
    (146, 'Oltinsoy', 'Oltinsoy', 'Алтынсайский',  11, now(), true, 'Oltinsoy tumani'),
-- Sirdaryo
    (147, 'Boyovut', 'Boyovut', 'Баяут',  12, now(), true, 'Boyovut Tumani'),
    (148, 'Sirdaryo', 'Sirdaryo', 'Сырдарья',  12, now(), true, 'Sirdaryo Tumani'),
    (149, 'Guliston', 'Guliston', 'Гулистан',  12, now(), true, 'Guliston'),
    (150, 'Mirzaobod', 'Mirzaobod', 'Мирзаабадский',  12, now(), true, 'Mirzaobod tumani'),
    (151, 'Oqoltin', 'Okoltin', 'Акалтынский',  12, now(), true, 'Oqoltin Tumani'),
    (152, 'Sardora', 'Sardoba', 'Сардобский',  12, now(), true, 'Sardoba Tumani'),
    (153, 'Shirin', 'Shirin', 'Ширин',  12, now(), true, 'Shirin'),
    (154, 'Xavos', 'Khavos', 'Хаваст',  12, now(), true, 'Xavos Tumani'),


-- Farg'ona
    (155, 'Oltiariq', 'Oltiarik', 'Алтыарык',  13, now(), true, 'Oltiariq Tumani'),
    (156, 'Bogʻdod', 'Bogdod', 'Багдад',  13, now(), true, 'Baghdod Tumani'),
    (157, 'Beshariq', 'Beshariq', 'Бешарык',  13, now(), true, 'Beshariq Tumani'),
    (158, 'Dangʻara', 'Dangara', 'Дангара',  13, now(), true, 'Danghara Tumani'),
    (159, 'Fargʻona', 'Fergana', 'Фергана',  13, now(), true, 'Farg''ona'),
    (160, 'Qoʻqon', 'Kukan', 'Коканд',  13, now(), true, 'Qo‘qon'),
    (161, 'Quva', 'Kuva', 'Кува',  13, now(), true, 'Quwa Tumani'),
    (162, 'Quvasoy', 'Kuvasoy', 'Кувасай', 13, now(), true, 'Quvasoy'),
    (163, 'Yozyovon', 'Yozyovon', 'Язъяванский',  13, now(), true, 'Yozyowon Tumani'),
    (164, 'Margʻilon', 'Margilan', 'Маргилан',  13, now(), true, 'Margilan'),
    (165, 'Qo‘shtepa', 'Kushtepa', 'Куштепинский',  13, now(), true, 'Qo‘shtepa'),
    (166, 'So‘x', 'Sukh', 'Сохский',  13, now(), true, 'Soʻx Tumani'),
    (167, 'Rishton', 'Rishton', 'Риштан',  13, now(), true, 'Rishton Tuman'),
    (168, 'Uchko‘prik', 'Uchkuprik', 'Учкуприкский',  13, now(), true, 'Uchkoʻprik tumani'),
    (169, 'Toshloq', 'Toshlok', 'Ташлак',  13, now(), true, 'Toshloq Tumani'),
    (170, 'O‘zbekiston', 'Uzbekistan', 'Узбекистанский', 13, now(), true, 'O‘zbekiston Tumani'),

-- Xorazm
    (171, 'Bogʻot', 'Bogot', 'Багат',  14, now(), true, 'Boghot Tumani'),
    (172, 'Gurlan', 'Gurlan', 'Гурлен',  14, now(), true, 'Gurlan Tumani'),
    (173, 'Yangibozor', 'Yangibozor', 'Янгибазарский',  14, now(), true, 'Yangibozor'),
    (174, 'Qoʻshkoʻpir', 'Kushkupir', 'Кошкупыр',  14, now(), true, 'Qoʻshkoʻpir Tumani'),
    (175, 'Tuproqqalʼa', 'Tuprokqala', 'Тупроккалинский',  14, now(), true, 'Tuproqqalʼa Tumani'),
    (176, 'Shovot', 'Shovot', 'Шават', 14, now(), true, 'Shovot Tumani'),
    (177, 'Urganch', 'Urganch', 'Ургенч',  14, now(), true, 'Urganch Tumani'),
    (178, 'Xonqa', 'Khonqa', 'Ханка',  14, now(), true, 'Xonqa Tumani'),
    (179, 'Xozarasp', 'Khozarasp', 'Хазарасп',  14, now(), true, 'Xozarasp Tumani'),
    (180, 'Xiva', 'Khiva', 'Хива',  14, now(), true, 'Xiva Tumani'),
    (181, 'Yangiariq', 'Yangiariq', 'Янгиарык',  14, now(), true, 'Yangiariq Tumani');