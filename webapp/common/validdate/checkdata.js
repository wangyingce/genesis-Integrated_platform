/**
 * @author user
 */
		YAHOO.namespace("quote.data");

		//�������е�У�����
		YAHOO.quote.data.zhsPar=/[\u4e00-\u9fa5]{2,}/;
		YAHOO.quote.data.idnoPar=/^\d{15}(\d{2}[A-Za-z0-9])?$|^\d{9}([A-Za-z0-9])?$|^\d{11}([A-Za-z0-9])?$/;
		YAHOO.quote.data.forbidPar=/\*|\@/;
		YAHOO.quote.data.numPar= /^(([+])?(0|([1-9][0-9]*))([.][0-9]+)?){1,12}$/;
		YAHOO.quote.data.moneyPar=/\d{1,10}/;
		YAHOO.quote.data.moneyPointPar=/^(\-?(0|[1-9][0-9]*)([.][0-9]{1,4})?){1,12}$/;
		YAHOO.quote.data.ratePar=/\d{1,4}\.\d{0,4}$|\d{1,4}$/;
		YAHOO.quote.data.money1Par = /^\-?[1-9]\d{0,2},(\d{3},)*\d{3}(\.\d+)?$|^\-?[1-9]\d{0,2}(\.\d+)?$|^\-0?(\.\d+)$|^\-0(\.\d+)?$|^0?(\.\d+)?/;
		YAHOO.quote.data.numSpacePar=/\d{1,}\ |\d{1,}$/g;
		YAHOO.quote.data.vinPar=/[A-Za-z0-9]{17}/;
		YAHOO.quote.data.agePar=/[0-9]{1,3}/;
		YAHOO.quote.data.newDicPar=/^@/;
		YAHOO.quote.data.errTipPar = /\[����\:/;
		YAHOO.quote.data.carNoPar=/^([\u4e00-\u9fa5]{1,2}|[WJ])[A-Za-z0-9]{1,2}[0-9A-Za-z]{4}[\u4e00-\u9fa5,0-9A-Za-z]{1}$/;
		YAHOO.quote.data.oNumberPar=/^[0-9]+$/;
		
		YAHOO.quote.data.deflossCarNoPar=/^([\u4e00-\u9fa5]{1,2}|[WJ])([0-9A-Z\u4e00-\u9fa5]{6,})/;
		
		//carNoValid �¼���У����� Terence 2010-04-30 16:07
		//δ���� 97 ʽ�侯������ ���磺WJ18-12345
		//2013046434ע�͵�֮ǰ�ĵĳ��ƺ���֤�����¹���ĳ��ƺ���֤ add by lvenchao start
		//YAHOO.quote.data.carNoValid = /(^[\u4e00-\u9fa5]{1}[A-Z0-9]{5,10}$)|(^WJ[0-9]{4,9}$)/;
		YAHOO.quote.data.carNoValid = /(^[\u4e00-\u9fa5]{1}[A-Z0-9]{5,10}$)|(^[A-Z]{2}[0-9]{4,9}$)/;
		//2013046434ע�͵�֮ǰ�ĵĳ��ƺ���֤�����¹���ĳ��ƺ���֤ add by lvenchao end
		//YAHOO.quote.data.datePar =/[\d]{4}[-][\d]{1,2}[-][\d]{1,2}/;
		//YAHOO.quote.data.datetimePar =/[\d]{4}[-][\d]{1,2}[-][\d]{1,2}/;
		

		//�������е�У�����ͣ���Ӧinput���class��
		YAHOO.quote.data.dcchkPar=/dc-chk/;
		YAHOO.quote.data.dttextPar=/dt-text/;
		YAHOO.quote.data.dtnumPar=/dt-num/;
		YAHOO.quote.data.dtmoneyPar=/dt-money/;
		YAHOO.quote.data.dtratePar=/dt-rate/;
		YAHOO.quote.data.dtdatePar=/dt-date/;
		YAHOO.quote.data.dtzhsPar=/dt-zhs/;
		YAHOO.quote.data.dtvinPar=/dt-vin/;
		YAHOO.quote.data.dtagePar=/dt-age/;
		YAHOO.quote.data.dtcarNoPar=/dt-carno/;
		YAHOO.quote.data.dtonumPar=/dt-onum/;
		
		//YAHOO.quote.data.dtdeflossCarNoPar=/dt-deflosscarno/;
		

		YAHOO.quote.data.lenPar=/[^\x00-\xff]/g;

		YAHOO.quote.data.strLen=function(){return this.replace(YAHOO.quote.data.lenPar,"aa").length;}
		YAHOO.quote.data.isDate=function(str){
			var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
			if(r==null)return false;
			var d = new Date(r[1], r[3]-1, r[4]);
			return(d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]);
		}
		YAHOO.quote.data.isTime=function(str){
			var r = str.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/);
			if(r==null)return false;
			var d = new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]);
			return (d.getFullYear()==r[1]&&(d.getMonth()+1)==r[3]&&d.getDate()==r[4]&&d.getHours()==r[5]&&d.getMinutes()==r[6]&&d.getSeconds()==r[7]);
		}

		//У������
		YAHOO.quote.data.datacheck=function(root){
			YAHOO.quote.data.datacheck.clearTips();
			var vFlag = true;
			var arrEl = YAHOO.util.Dom.getElementsBy(YAHOO.quote.data.datacheck.isElement,"input",root);
			for(var i=0;i<arrEl.length;i++){
				var el = arrEl[i];
				//�Ƿ񳬳�

				//�Ƿ�Ϊ��:���Ҫ��Ϊ�ն�Ϊ�գ���ֱ���״�
				if(YAHOO.quote.data.dcchkPar.test(el.className)){
					if(trim(el.value)==""){
						YAHOO.quote.data.datacheck.adderror(el,"�����Ǳ����������д��Ϣ������ȷ��д��");
						vFlag = false;
					}
				}
				//��������
				if(YAHOO.quote.data.dtnumPar.test(el.className)){
					if(trim(el.value)!=""&&!YAHOO.quote.data.numPar.test(el.value)){
						YAHOO.quote.data.datacheck.adderror(el,"���������дһ����ȷ������!����ϸ��飡");
						vFlag = false;
					}
				}
				//��������
				if(YAHOO.quote.data.dtdatePar.test(el.className)){
					if(trim(el.value)!=""&&!YAHOO.quote.data.isDate(el.value)){
						YAHOO.quote.data.datacheck.adderror(el,"���������дһ����ȷ������(YYYY-MM-DD)������ϸ��飡");
						vFlag = false;
					}
				}
				//��������
				if(YAHOO.quote.data.dtmoneyPar.test(el.className)){
					if(trim(el.value)!="" && !YAHOO.quote.data.moneyPointPar.test(el.value)){
						YAHOO.quote.data.datacheck.adderror(el,"���������дһ����ȷ������������ϸ��飡");
						vFlag = false;
					}
				}
				//��������
				if(YAHOO.quote.data.dtratePar.test(el.className)){
					if(trim(el.value)!=""&&!YAHOO.quote.data.ratePar.test(el.value)){
						YAHOO.quote.data.datacheck.adderror(el,"���������дһ����ȷ����ֵ,����0.1234������ϸ��飡");
						vFlag = false;
					}
				}
				//����������
				if(YAHOO.quote.data.dtzhsPar.test(el.className)){
					if(trim(el.value)!=""&&! YAHOO.quote.data.zhsPar.test(el.value)){
						YAHOO.quote.data.datacheck.adderror(el,"���������д��ȷ�����֣�����ϸ��飡");
						vFlag = false;
					}
				}
				//У��VIN��
				if(YAHOO.quote.data.dtvinPar.test(el.className)){
					if(trim(el.value)!=""&&! YAHOO.quote.data.vinPar.test(el.value)){
						YAHOO.quote.data.datacheck.adderror(el,"���������д17λVIN�룡����ϸ���VIN���Ƿ�Ϊ17λ��");
						vFlag = false;
					}
				}

				//У������
				if(YAHOO.quote.data.dtagePar.test(el.className)){
					if(trim(el.value)!=""&&! YAHOO.quote.data.agePar.test(el.value)){
						YAHOO.quote.data.datacheck.adderror(el,"����д��ȷ�����䣡");
						vFlag = false;
					}
				}
				
				//����У�鳵�ƺ�
				/*if(YAHOO.quote.data.dtdeflossCarNoPar.test(el.className)){
					if(trim(el.value)!=""&&! YAHOO.quote.data.deflossCarNoPar.test(el.value)){
						YAHOO.quote.data.datacheck.adderror(el,"����д��ȷ�ĳ��ƺ�,�����Ժ��ֻ���WJ��ͷ���������֡���ĸ���ֲ�С����λ");
						vFlag = false;
					}
				}*/
				
				//У�鳵�ƺ�
				if(YAHOO.quote.data.dtcarNoPar.test(el.className)){
					if(trim(el.value)!=""&&! YAHOO.quote.data.carNoValid.test(el.value)){
						//���ƺ��޸���ʾ���� add by lvenchao start
						//YAHOO.quote.data.datacheck.adderror(el,"����д��ȷ�ĳ��ƺ�,�����Ժ��ֻ���WJ��ͷ���³�������������[��000000]");
						YAHOO.quote.data.datacheck.adderror(el,"����д��ȷ�ĳ��ƺ�,�����Ժ��ֻ���������дӢ����ĸ��ͷ���³�������������[��000000]");
						//���ƺ��޸���ʾ���� add by lvenchao end
						vFlag = false;
					}
				}
				//ֻУ��������ɵ��ַ���
				if(YAHOO.quote.data.dtonumPar.test(el.className)){
					if(trim(el.value)!=""&&! YAHOO.quote.data.oNumberPar.test(el.value)){
						YAHOO.quote.data.datacheck.adderror(el,"���������дһ����ȷ������!����ϸ��飡");
						vFlag = false;
					}
				}
				/*OA 25000-����-���������ά����ʦ��(2012-07-17 165406) ��չ by aijian����xtypeΪnonePoint������ʾ���� 2012-08-02 */
				if(el.xtype && el.xtype != 'undefined' && 'nonePoint'==el.xtype){
					var elStr = el.value;
					if(elStr != null && elStr != ''   && elStr.indexOf(".") != -1){
						YAHOO.quote.data.datacheck.adderror(el,"���ﲻ������д����С���������");
						vFlag = false;
					}
				}
			}
			return vFlag;
		}
		YAHOO.quote.data.datacheck.errTips=[];
//		YAHOO.quote.data.datacheck.errObjs=[];
		YAHOO.quote.data.datacheck.clearTips=function(){
			for(var i=0;i<YAHOO.quote.data.datacheck.errTips.length;i++){
				var errtip = YAHOO.quote.data.datacheck.errTips[i];
				var el = errtip._context;
				if(false){
					el.title="";
					YAHOO.util.Dom.removeClass(el,"dc-err");
				}else{
					el[0].title="";
					YAHOO.util.Dom.removeClass(el[0],"dc-err");
				}
				errtip.destroy();;
			}
			YAHOO.quote.data.datacheck.errTips=[];
		}
		//����el��id����ȡ��Ӧ��tip��id��
		YAHOO.quote.data.datacheck.getTipNameByEl=function(el,tname){
			if(el.id!=""){
				tname = "errtip_"+el.id;
			}else{
				tname = "errtip_"+el.name;
			}
//			alert("name is "+tname);
			return tname;
		}

		/**
		 * ���Ӵ�����Ϣ
		 * @param {String|HTMLElement} el
		 * @param {String} msg
		 */
		YAHOO.quote.data.datacheck.adderror=function(el,msg){
			el.title = el.title + "[����: "+msg+" ]";
			var tipname = new YAHOO.quote.data.datacheck.getTipNameByEl(el,tipname);
			tipname = ""+tipname;
			var errtip = new YAHOO.widget.Tooltip( tipname.toString(),{context:el,zIndex:300});
			YAHOO.quote.data.datacheck.errTips[ YAHOO.quote.data.datacheck.errTips.length ] = errtip;
			//YAHOO.quote.data.datacheck.errObjs[ YAHOO.quote.data.datacheck.errObjs.length ] = el;
			YAHOO.util.Dom.addClass(el,"dc-err");
		}
		//�ж��Ƿ�Ϊָ��������У�����
		YAHOO.quote.data.datacheck.isElement=function(el){
			return true;
		}
