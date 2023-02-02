<#macro login path isRegisterform>
    <form action="${path}" method="post">
        <div><label> User Name : <input type="text" name="username" placeholder="User name"/> </label></div>
        <div><label> Password: <input type="password" name="password" placeholder="Password"/> </label></div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />

        <#if !isRegisterform>
            <button type="submit">Sign in</button>
        <#else >
            <div><label> Email: <input type="email" name="email" placeholder="some@some.com"/> </label></div>
            <button type="submit">Create</button>
        </#if>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button type="submit">Sign Out</button>
    </form>
</#macro>