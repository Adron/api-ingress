# Tweaks & Receipts for RESTful API end points



Testing:

``` terminal
curl -X POST http://localhost:8080/initiate -d "initiate" -H "Content-Type: text/plain"
```

``` terminal
curl -X POST \
  http://your-api-url/initiate \
  -H 'Content-Type: text/plain' \
  -H 'X-From: your_from_phone_number' \
  -H 'X-To: your_to_phone_number' \
  -H 'X-Policy-Variable: x-account-id=your_account_id,x-account-name=your_account_name,x-subaccount-id=your_subaccount_id,x-source-ip=your_source_ip,x-source-type=your_source_type,x-account-created=your_account_created,x-smpp-tag-source-entity=your_smpp_tag_source_entity,x-smpp-tag-account-id=your_smpp_tag_account_id,x-smpp-tag-campaign-id=your_smpp_tag_campaign_id,x-from-assigned=your_from_assigned,x-cos=your_cos' \
  -d 'your_request_body'
```
