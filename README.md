![schema](https://user-images.githubusercontent.com/80293325/231624187-7ea1e43b-3917-4e4f-b298-191c718e1b74.png)



ADD USER
http://localhost:8085/api/users
` {
  "name" : "Marcelo",
  "password" :  "falchizao",
  "email" : "marcelonavarro@gmail.com"
}`


LOGIN
http://localhost:8085/api/login
`{
  "email" : "marcelonavarro@gmail.com",
  "password" :  "falchizao",
}`


ADD EQUIP
http://localhost:8085/api/equipments/
` {
  "name" : "maquina1",
  "value_hour_utfpr" : 10.0,
  "value_hour_partner" : 12.0,
  "value_hour_pf_pj" : 15.0,
  "value_sample_utfpr" : 20,
  "value_sample_partner" : 25,
  "value_sample_pf_pj" : 30
} `





