<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Add Promote</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }
        .container {
            max-width: 800px;
            margin: 50px auto;
            background: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
        }
        .form-group {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }
        label {
            font-weight: bold;
            margin-right: 10px;
        }
        .col-md-2 {
            flex: 0 0 20%;
            max-width: 20%;
            text-align: right;
            padding-right: 10px;
        }
        .col-md-10 {
            flex: 1;
        }
        input, button {
            padding: 10px;
            width: 100%;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
        }
        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
            margin-top: 10px;
        }
        button:hover {
            background-color: #0056b3;
        }
        .back-button {
            background-color: #6c757d;
        }
        .back-button:hover {
            background-color: #5a6268;
        }
        .button-group {
            display: flex;
            justify-content: space-between;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Add Promote</h1>
        <form action="${pageContext.request.contextPath}/admin/promote" method="post">
            
            <div class="form-group">
                <label class="col-md-2 control-label">Voucher Code: <span class="required">* </span></label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="voucherCode" placeholder="Enter voucher code" required />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">Start Date: <span class="required">* </span></label>
                <div class="col-md-10">
                    <input type="datetime-local" class="form-control" name="startDate" required />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">End Date: <span class="required">* </span></label>
                <div class="col-md-10">
                    <input type="datetime-local" class="form-control" name="endDate" required />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">Discount Percent: <span class="required">* </span></label>
                <div class="col-md-10">
                    <input type="number" class="form-control" name="discountPercent" placeholder="Enter discount percent" required />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">Quantity: <span class="required">* </span></label>
                <div class="col-md-10">
                    <input type="number" class="form-control" name="quantity" placeholder="Enter quantity" required />
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-2 control-label">Min Order Total: <span class="required">* </span></label>
                <div class="col-md-10">
                    <input type="number" step="0.01" class="form-control" name="minOrderTotal" placeholder="Enter minimum order total" required />
                </div>
            </div>
            
            <div class="button-group">
                <button type="submit">Save</button>
                <a href="${pageContext.request.contextPath}/admin/promote">
                    <button type="button" class="back-button">Back</button>
                </a>
            </div>
        </form>
    </div>
</body>
</html>
