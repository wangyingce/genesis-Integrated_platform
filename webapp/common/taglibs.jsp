<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="app" uri="/WEB-INF/tlds/app.tld" %>
<%@ taglib prefix="ce" uri="/WEB-INF/tlds/ce.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<fmt:setLocale value="${header['accept-language']}"/>
<fmt:setBundle basename="i18n/messages"/>
<script type="text/javascript">
  //for global use
  var contextRootPath = "${ctx}";
</script>
