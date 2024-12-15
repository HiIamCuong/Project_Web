<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- SEARCH FORM -->
<li class="menu-search">
    <span class="sep"></span>
    <i class="fa fa-search search-btn"></i>
    <div class="search-box">
        <form action="/search" method="GET"> <!-- POST the search query to /search endpoint -->
            <div class="input-group">
                <input type="text" name="query" placeholder="Search" class="form-control" required>
                <span class="input-group-btn">
                    <button class="btn btn-primary" type="submit">Search</button>
                </span>
            </div>
        </form>
    </div> 
</li>
