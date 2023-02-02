<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
    <div>
        <@l.logout/>
    </div>
    <div>
        Add message:
        <form method="post">
            <input name="text" type="text" placeholder="Enter text">
            <input name="tag" type="text" placeholder="Enter tag">
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add message</button>
        </form>
    </div>
    <div>
        Filter message:
        <form method="get" action="/main">
            <input name="filter" type="text" placeholder="Enter tag">
            <button type="submit">Filter message</button>
        </form>
    </div>
        Messages:
        <div>
            <table>
                <tr>
                    <th>ID</th>
                    <th>MESSAGE</th>
                    <th>TAG</th>
                    <th>USER</th>
                </tr>
                <#list messages as message>
                    <tr>
                        <td>${message.id}</td>
                        <td>${message.text}</td>
                        <td>${message.tag}</td>
                        <td>${message.author}</td>
                    </tr>
                <#else>
                    No messages.
                </#list>
            </table>
        </div>
</@c.page>
