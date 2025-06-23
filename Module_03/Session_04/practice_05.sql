USE `practice05_db`;

--
-- EX02
--

ALTER TABLE `building`
ADD (
	CONSTRAINT `host_building_fk`
	FOREIGN KEY (`host_id`)
	REFERENCES `host`(`id`),
    CONSTRAINT `contractor_building_fk`
    FOREIGN KEY (`contractor_id`)
    REFERENCES `contractor`(`id`)
);

ALTER TABLE `design`
ADD (
	CONSTRAINT `building_design_fk`
    FOREIGN KEY (`building_id`)
    REFERENCES `building`(`id`),
    CONSTRAINT `architect_design_fk`
    FOREIGN KEY (`architect_id`)
    REFERENCES `architect`(`id`)
);

ALTER TABLE `work`
ADD (
	CONSTRAINT `building_work_fk`
    FOREIGN KEY (`building_id`)
    REFERENCES `building`(`id`),
    CONSTRAINT `worker_work_fk`
    FOREIGN KEY (`worker_id`)
    REFERENCES `worker`(`id`)
);

--
-- EX03
-- 
-- Hiển thị thông tin công trình có chi phí cao nhất
SELECT *
FROM `building`
WHERE `cost` = (
	SELECT MAX(cost) AS 'Công trình đắt nhất' 
    FROM `building`
);

-- Hiển thị thông tin công trình có chi phí lớn hơn tất cả các công trình 
-- được xây dựng ở Cần Thơ
SELECT *
FROM `building`
WHERE `cost` > ALL (
	SELECT `cost`
    FROM `building` 
    WHERE `city` = 'can tho'
);

SELECT *
FROM `building`
WHERE `cost` > (
	SELECT MAX(`cost`)
    FROM `building`
    WHERE `city` = 'can tho'
);
-- Hiển thị thông tin công trình có chi phí lớn hơn một trong
-- các công trình được xây dựng ở Cần Thơ
SELECT *
FROM `building`
WHERE `city` <> 'can tho' AND `cost` > ANY (
	SELECT `cost`
    FROM `building`
    WHERE `city` = 'can tho'
);

-- Hiển thị thông tin công trình chưa có kiến trúc sư thiết kế
SELECT *
FROM `building`
LEFT JOIN `design` ON `building`.`id` = `design`.`building_id`
WHERE `design`.`architect_id` IS NULL;

SELECT *
FROM `building`
WHERE id NOT IN (
	SELECT DISTINCT building_id
    FROM design
);

-- Hiển thị thông tin các kiến trúc sư cùng năm sinh và cùng nơi tốt nghiệp
SELECT *
FROM `architect`
WHERE (`birthday`, `place`) IN (
	SELECT `birthday`, `place`
	FROM `architect`
	GROUP BY `birthday`, `place`
	HAVING COUNT(`id`) >= 2
);

--
-- EX04
--
-- Hiển thị thù lao trung bình của từng kiến trúc sư
SELECT ar.`name`, AVG(de.`benefit`) AS 'Thù lao trung bình'
FROM `architect` AS ar
JOIN `design` AS de ON de.`architect_id` = ar.`id`
GROUP BY ar.`name`;

-- Hiển thị chi phí đầu tư cho các công trình ở mỗi thành phố
SELECT `city`, SUM(`cost`) AS 'Tổng chi phí đầu tư'
FROM `building`
GROUP BY `city`;

-- Tìm các công trình có chi phí trả cho kiến trúc sư lớn hơn 50
SELECT bu.`name`, SUM(de.`benefit`)
FROM `building` AS bu
JOIN `design` AS de ON de.`building_id` = bu.`id`
GROUP BY de.`building_id`
HAVING SUM(de.`benefit`) > 50;

-- Tìm các thành phố có ít nhất HAI kiến trúc sư tốt nghiệp
SELECT `place`
FROM `architect`
GROUP BY `place`
HAVING COUNT(`id`) >= 2;

--
-- EX05
--
-- Hiển thị tên công trình, tên chủ nhân và tên chủ thầu của công trình đó
SELECT `building`.`name` AS 'tên công trình',
		`host`.`name` AS 'tên chủ đầu tư',
        `contractor`.`name` AS 'tên chủ thầu'
FROM `building`
LEFT JOIN `host` ON `building`.`host_id` = `host`.`id`
LEFT JOIN `contractor` ON `building`.`contractor_id` = `contractor`.`id`;

-- Hiển thị tên công trình (building), tên kiến trúc sư (architect) và thù lao của 
-- kiến trúc sư ở mỗi công trình (design)
SELECT `building`.`name` AS 'tên công trình',
		`architect`.`name` AS 'tên kiến trúc sư',
		`design`.`benefit` AS 'thù lao'
FROM `building`
LEFT JOIN `design` ON `building`.`id` = `design`.`building_id`
LEFT JOIN `architect` ON `architect`.`id` = `design`.`architect_id`;

-- Hãy cho biết tên và địa chỉ công trình (building) do chủ thầu 
-- Công ty xây dựng số 6 thi công (contractor)
SELECT `building`.`name` AS 'tên công trình',
		`building`.`address` AS 'địa chỉ công trình',
        `contractor`.`name` AS 'tên chủ thầu'
FROM `building`
JOIN `contractor` ON `building`.`contractor_id` = `contractor`.`id`
WHERE `contractor`.`name` = 'cty xd so 6';

-- Tìm tên và địa chỉ liên lạc của các chủ thầu (contractor) thi công công trình ở
-- Cần Thơ (building) do kiến trúc sư Lê Kim Dung thiết kế (architect, design)
SELECT bu.`name` AS 'tên công trình',
		bu.`city` AS 'địa điểm công trình',
        ar.`name` AS 'tên kiến trúc sư',
        co.`name` AS `tên chủ thầu`,
        co.`address` AS `địa chỉ liên lạc chủ thầu`
FROM `building` bu
JOIN `contractor` co ON bu.`contractor_id` = co.`id`
JOIN `design` de ON de.`building_id` = bu.`id`
JOIN `architect` ar ON ar.`id` = de.`architect_id`
WHERE ar.`name` = 'Lê Kim Dung' AND bu.`city` = 'can tho';

-- Hãy cho biết nơi tốt nghiệp của các kiến trúc sư (architect) đã thiết kế (design)
-- công trình Khách Sạn Quốc Tế ở Cần Thơ (building)
SELECT `building`.`name` AS 'tên công trình',
		`building`.`city` AS 'địa điểm công trình',
		`architect`.`name` AS 'tên kiến trúc sư',
		`architect`.`place` AS 'nơi tốt nghiệp'
FROM `building`
JOIN `design` ON `building`.`id` = `design`.`building_id`
JOIN `architect` ON `architect`.`id` = `design`.`architect_id`
WHERE `building`.`city` = 'can tho' AND `building`.`name` LIKE '%khach san%';

-- Cho biết họ tên, năm sinh, năm vào nghề của các công nhân có chuyên môn hàn
-- hoặc điện (worker) đã tham gia các công trình (work) mà chủ thầu Lê Văn Sơn
-- (contractor) đã trúng thầu (building)
SELECT co.`name` AS 'tên chủ thầu',
		bu.`name` AS 'tên công trình',
        woe.*
FROM `building` bu
JOIN `contractor` co ON co.`id` = bu.`contractor_id`
JOIN `work` wo ON wo.`building_id` = bu.`id`
JOIN `worker` woe ON woe.`id` = wo.`worker_id`
WHERE co.`name` = 'le van son' AND woe.`skill` IN ('han', 'dien');

SELECT wkr.name, wkr.skill, wk.building_id FROM work AS wk
INNER JOIN worker AS wkr
ON wkr.id = wk.worker_id
WHERE wk.building_id IN (
	SELECT b.id FROM building AS b
	INNER JOIN contractor AS c
	ON b.contractor_id = c.id
	WHERE c.`name` = 'le van son'
) AND wkr.skill IN ('han', 'dien');

-- Những công nhân nào (worker) đã bắt đầu tham gia công trình Khách sạn Quốc
-- Tế ở Cần Thơ (building) trong giai đoạn từ ngày 15/12/1994 đến 31/12/1994
-- (work) số ngày tương ứng là bao nhiêu


-- Cho biết họ tên và năm sinh của các kiến trúc sư đã tốt nghiệp ở TP Hồ Chí
-- Minh (architect) và đã thiết kế ít nhất một công trình (design) có kinh phí đầu tư
-- trên 400 triệu đồng (building)
SELECT ar.`name` AS 'tên kiến trúc sư',
		ar.`birthday` AS 'năm sinh',
        ar.`place` AS 'nơi tốt nghiệp',
        bu.`name` AS 'tên công trình',
        bu.`cost` AS 'kinh phí đầu tư'
FROM `building` bu
JOIN `design` de ON de.`building_id` = bu.`id`
JOIN `architect` ar ON ar.`id` = de.`architect_id`
WHERE bu.`cost` >= 400 AND ar.`place` = 'tp hcm';

-- Cho biết tên công trình có kinh phí cao nhất
SELECT `name`
FROM `building`
WHERE `cost` = (
	SELECT MAX(`cost`)
	FROM `building`
);

-- Cho biết tên các kiến trúc sư (architect) vừa thiết kế các công trình (design) do
-- Phòng dịch vụ sở xây dựng (contractor) thi công vừa thiết kế các công trình do
-- chủ thầu Lê Văn Sơn thi công
SELECT ar.`name` AS 'tên kiến trúc sư'
FROM `architect` ar
JOIN `design` de ON ar.`id` = de.`architect_id`
JOIN `building` bu ON bu.`id` = de.`building_id`
JOIN `contractor` co ON co.`id` = bu.`contractor_id`
WHERE co.`name` IN ('le van son', 'phong dich vu so xd')
GROUP BY ar.`name`
HAVING COUNT(DISTINCT co.`id`) = 2;

-- Cho biết họ tên các công nhân (worker) có tham gia (work) các công trình ở Cần
-- Thơ (building) nhưng không có tham gia công trình ở Vĩnh Long
SELECT bu.`name` AS `tên công trình`,
		bu.`city` AS `địa điểm công trình`,
        woe.`name` AS `tên công nhân`
FROM `building` bu
JOIN `work` wo ON wo.`building_id` = bu.`id`
JOIN `worker` woe ON woe.`id` = wo.`worker_id`
WHERE woe.`id` IN (
	SELECT `work`.`worker_id` 
    FROM `work` 
    JOIN `building` ON `building`.`id` = `work`.`building_id` 
    WHERE `building`.`city` = 'can tho')
AND woe.`id` NOT IN (
	SELECT `work`.`worker_id` 
    FROM `work` 
    JOIN `building` ON `building`.`id` = `work`.`building_id` 
    WHERE `building`.`city` = 'vinh long');

-- Cho biết tên của các chủ thầu đã thi công các công trình có kinh phí lớn hơn tất
-- cả các công trình do chủ thầu phòng Dịch vụ Sở xây dựng thi công
SELECT co.`name`, bu.`name`, bu.`cost`
FROM `contractor` co
JOIN `building` bu ON bu.`contractor_id` = co.`id`
WHERE bu.`cost` > ALL (
	SELECT bu2.`cost`
    FROM `building` bu2
    JOIN `contractor` co2 ON bu3.`contractor_id` = co2.`id`
    WHERE co2.`name` = 'phong dich vu so xd'
);

-- Cho biết họ tên các kiến trúc sư có thù lao thiết kế một công trình nào đó dưới
-- giá trị trung bình thù lao thiết kế cho một công trình
SELECT ar.`name` AS 'tên kiến trúc sư',
		bu.`name` AS 'tên công trình',
        bu.`cost` AS 'thù lao thiết kế'
FROM `architect` ar
JOIN `design` de ON de.`architect_id` = ar.`id`
JOIN `building` bu ON bu.`id` = de.`building_id`
WHERE bu.`cost` < (
	SELECT AVG(`cost`)
    FROM `building`
);

-- Tìm tên và địa chỉ những chủ thầu đã trúng thầu công trình có kinh phí thấp nhất
SELECT co.`name` AS 'tên chủ thầu',
		co.`address` AS 'địa chỉ chủ thầu',
		bu.`name` AS 'tên công trình',
		bu.`cost` AS 'giá trúng thầu'
FROM `contractor` co
JOIN `building` bu ON bu.`contractor_id` = co.`id`
WHERE bu.`cost` = (
	SELECT MIN(`cost`)
    FROM `building`
);
        
-- Tìm họ tên và chuyên môn của các công nhân (worker) tham gia (work) các công
-- trình do kiến trúc sư Le Thanh Tung thiet ke (architect) (design)
SELECT woe.`name` AS 'tên công nhân',
		woe.`skill` AS 'chuyên môn',
        bu.`name` AS 'tên công trình',
        ar.`name` AS 'tên kiến trúc sư'
FROM `building` bu
JOIN `work` wo ON wo.`building_id` = bu.`id`
JOIN `worker` woe ON woe.`id` = wo.`worker_id`
JOIN `design` de ON de.`building_id` = bu.`id`
JOIN `architect` ar ON ar.`id` = de.`architect_id`
WHERE ar.`name` = 'le thanh tung';

-- Tìm các cặp tên của chủ thầu có trúng thầu các công trình tại cùng một thành phố
SELECT bu.`city` AS 'thành phố',
		co.`name` AS 'tên chủ thầu'
FROM `building` bu
JOIN `contractor` co ON co.`id` = bu.`contractor_id`
GROUP BY bu.`city`, co.`name`
ORDER BY bu.`city`;

-- Tìm tổng kinh phí của tất cả các công trình theo từng chủ thầu
SELECT SUM(`cost`) AS 'tổng kinh phí', co.`name`
FROM `building` bu
JOIN `contractor` co ON co.`id` = bu.`contractor_id`
GROUP BY co.`name`;

-- Cho biết họ tên các kiến trúc sư có tổng thù lao thiết kế các công trình lớn hơn 25 triệu
SELECT ar.`name`, SUM(`benefit`) AS 'tổng thù lao'
FROM `architect` ar
JOIN `design` de ON de.`architect_id` = ar.`id`
GROUP BY ar.`name`
HAVING SUM(`benefit`) > 25;

-- Cho biết số lượng các kiến trúc sư có tổng thù lao thiết kế các công trình lớn hơn 25 triệu
SELECT COUNT(*) AS 'số kiến trúc sư'
FROM (
	SELECT ar.`name`
	FROM `architect` ar
	JOIN `design` de ON de.`architect_id` = ar.`id`
	GROUP BY ar.`name`
	HAVING SUM(`benefit`) > 25
) AS sl;

-- Tìm tổng số công nhân đã tham gia ở mỗi công trình
SELECT `building_id`, COUNT(`worker_id`) AS 'tổng số công nhân'
FROM `work`
GROUP BY `building_id`;

-- Tìm tên và địa chỉ công trình có tổng số công nhân tham gia nhiều nhất
SELECT `building`.`name`,
		`building`.`address`,
		COUNT(`work`.`worker_id`)
FROM `building`
JOIN `work` ON `work`.`building_id` = `building`.`id`
GROUP BY `building`.`name`, `building`.`address`
HAVING COUNT(`work`.`worker_id`) = (
	SELECT MAX(`so_luong`)
	FROM (
		SELECT COUNT(*) AS `so_luong`
		FROM `work`
		GROUP BY `building_id`
	) AS dem
);

-- Cho biêt tên các thành phố và kinh phí trung bình cho mỗi công trình của từng
-- thành phố tương ứng
SELECT DISTINCT bu.`city`, AVG(`cost`) AS 'chi phí tb'
FROM `building` bu
GROUP BY bu.`city`;

-- Cho biết họ tên các công nhân có tổng số ngày tham gia vào các công trình lớn
-- hơn tổng số ngày tham gia của công nhân Nguyễn Hồng Vân

-- Cho biết tổng số công trình mà mỗi chủ thầu đã thi công tại mỗi thành phố
SELECT co.`name`, bu.`city`, COUNT(bu.`id`) AS 'số công trình'
FROM `building` bu
JOIN `contractor` co ON co.`id` = bu.`contractor_id`
GROUP BY co.`id`, bu.`city`;

-- Cho biết họ tên công nhân có tham gia ở tất cả các công trình
SELECT wor.`name`, COUNT(`building_id`)
FROM `worker` wor
JOIN `work` w ON w.`worker_id` = wor.`id`
JOIN `building` bu ON bu.`id` = w.`building_id`
GROUP BY wor.`id`
HAVING COUNT(`building_id`) = (
	SELECT COUNT(`id`)
    FROM `building`
);

