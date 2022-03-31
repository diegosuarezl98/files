/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  diego
 * Created: Feb 20, 2022
 */

CREATE TABLE userRegistry (`id` int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
'username' varchar(100) NOT NULL,
'email' varchar(100) NOT NULL, 
'password' varchar(100) NOT NULL);