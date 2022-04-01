<script>
    export default {
        methods: {
            exportExcel(tableData) {
                console.log(tableData)
                require.ensure([], () => {
                    const { export_json_to_excel } = require('@/excel/Export2Excel');
                    const tHeader = ['创建时间', '提交时间', '所属省份', '所属城市', '门店编号', '门店名称', '门店简称', '负责人', '负责人手机号', '门店面积', '门店地址', '经营区域', '工行提交时间', '工行审核时间', '农行提交时间', '农行审核时间', '签约状态', '合作状态', '营业状态', '邀请人', '邀请人手机号', '邀请码', '商品数', '会员数', '订单数', '配送员数量', '备注'];
                    // 上面设置Excel的表格第一行的标题
                    const filterVal = ['createTime', 'settleSubmitTime', 'provinceName', 'cityName', 'shopNo', 'shopName', 'shopShort', 'shopHead', 'headMobile', 'shopAcreage', 'shopAddress', 'martName', 'bankSubmitTime', 'bankAuditTime', 'abcBankSubmitTime', 'abcBankAuditTime', 'isSigning', 'cooperation', 'shopStatus', 'inviter', 'inviterMobile', 'inviteCode', 'productNum', 'vipNum', 'orderNum', 'deliveryNum', 'remark'];
                    // 上面的index、nickName、name是tableData里对象的属性
                    const data = this.formatJson(filterVal, tableData);
                    export_json_to_excel(tHeader, data, '列表excel');
                })
            },
            formatJson(filterVal, jsonData) {
                return jsonData.map(v => filterVal.map(j => v[j]))
            }
        }
    }
</script>