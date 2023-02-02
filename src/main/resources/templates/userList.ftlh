<#import "parts/common.ftl" as c>

<@c.page>
        <#list users as user>
            <div>${user.id} ${user.username} <a href="/users/${user.id}">edit</a></div>
        <#else>
            No users.
        </#list>
</@c.page>