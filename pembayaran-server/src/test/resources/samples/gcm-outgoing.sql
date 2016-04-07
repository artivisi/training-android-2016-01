delete from t_gcm_outgoing_message;
insert into t_gcm_outgoing_message (id, terakhir_update, message_to, status, data)
values (
  'test',
  TIMESTAMP '2016-04-01 00:00:00',
  'dHmXzM6kwek:APA91bE09D42OAC6_BXanxH4SvuluHv1hgeYApqcc_tG5Ky1fXVGNfELyIGY52UJzl5ix5EJnbu09ki0FWZ03_uOKcAw45pDQ13aSFyr3IIVZnS3peJme7ZgmHzFexDNx6RJHDrlSLLV',
  'NEW',
  '{"pesan":"test"}'
);